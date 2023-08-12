import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtResponse } from '../models/JwtResponse';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  public login(user: {email: string, password: string}):Observable<JwtResponse>{
    return this.http.post<JwtResponse>(AUTH_API + 'signin',{
      email: user.email,
      password: user.password
    },httpOptions);
  }

  public register(user: any): Observable<any>{
    return this.http.post(AUTH_API+'signup', user ,httpOptions);
  }
}
