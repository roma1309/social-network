import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../service/token-storage.service';

const TOKEN_HEADER_KEY='Authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(private tokenService:TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = req
    const token = this.tokenService.getToken();
    if(token != null){
      const jwt = "Bearer "+ token;
      authRequest = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY,jwt)});
    }
    return next.handle(authRequest);
  }
 }

 export const authIntercepterProviders=[
  {
    provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi:true
  }
];

