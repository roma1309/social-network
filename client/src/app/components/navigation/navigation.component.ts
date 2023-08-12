import { Component } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { User } from 'src/app/models/User';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  isLoggedIn=false;
  isDataLoaded=false;
  role!: string;
  user!: User;

  constructor(private tokenService:TokenStorageService,
    private userService:UserService,
    private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !! this.tokenService.getToken();

    if(this.isLoggedIn){
      this.userService.getCurrentUser()
      .subscribe(data => {
        this.user=data;
        this.isDataLoaded=true;
        this.role = data.role;
      })
    }
  }

  logout(): void{
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }
}
