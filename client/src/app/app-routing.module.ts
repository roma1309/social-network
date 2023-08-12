import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AuthGuardService } from './helper/auth-guard.service';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  { path:'login',   component:LoginComponent },
  { path:'register',   component:RegisterComponent },
  { path:'main',   component:MainComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
