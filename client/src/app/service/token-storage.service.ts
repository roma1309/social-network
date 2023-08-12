import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
 private roles: Array<string> = [];

  constructor() { }

  public saveToken(token:string): void{
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,token);
  }

  public getToken(): null|string{
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void{
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY,JSON.stringify(user));
  }

  public getUser(): null|string{
    return sessionStorage.getItem(USER_KEY);
  }

  logOut(): void{
    window.sessionStorage.clear();
    window.location.reload();
  }
  
  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(TOKEN_KEY)) {
      console.log('test');
      console.log(sessionStorage.getItem(AUTHORITIES_KEY));
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)  || '{}' ).forEach((authority: string) => {
        this.roles.push(authority);
      });
    }
    return this.roles;
  }
}
