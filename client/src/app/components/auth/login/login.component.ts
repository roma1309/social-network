import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public loginForm!: FormGroup;
  roles: string[] = [];

  constructor(private authService:AuthService,
    private tokenStorage:TokenStorageService,
    // private notificationService:NotificationService,
    private router:Router,
    private fb:FormBuilder) {
      if(this.tokenStorage.getUser()){
        this.router.navigate(['main']);
      }
   }

   ngOnInit(): void {
    this.loginForm = this.createLoginForm();
  }

   createLoginForm():FormGroup{
    return this.fb.group({
      email:['',Validators.compose([Validators.required, Validators.email])],
      password:['',Validators.compose([Validators.required])]
      }
    )
  }

  submit():void{
    this.authService.login({
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    }).subscribe({next : (data) => {
      this.tokenStorage.saveToken(data.jwt);
      this.tokenStorage.saveUser(data);
     // this.notificationService.showSnackBar("Успешно");
      this.router.navigate(['/main']);
      window.location.reload();
      console.log(data.jwt);
    }, error : err =>{
    console.log(err);
   // this.notificationService.showSnackBar(err.message);
    }
  });
  }
}
