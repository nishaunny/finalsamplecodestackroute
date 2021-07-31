import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user';
import { RouteService } from './route.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authURL: string;
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  userName!: BehaviorSubject<any>;
  constructor(private httpClient: HttpClient,private routeService: RouteService) {
    if (this.getBearerToken() != null) {
      this.loggedIn.next(true);
    }
    this.userName = new BehaviorSubject<any>(sessionStorage.getItem("userName")!);
   
    this.authURL = 'http://localhost:9095/auth/user/login';
  }

  get isUserLoggedIn() {
    return this.loggedIn.asObservable();
  }
  get user() {
    return this.userName.asObservable();
  }

  adduser(user: User): Observable<any> {
    console.log(user)
    return this.httpClient.post<User>('http://localhost:9095/auth/user/adduser', user);
  }
  authenticateUser(user: User) {
    return this.httpClient.post(this.authURL, user);
  }

  setBearerToken(token: string,uName:string) {
    
    sessionStorage.setItem('myToken', token);
    
    if (token != null) {
      this.loggedIn.next(true);
    }
    sessionStorage.setItem('userName',uName);
    const userName = sessionStorage.getItem('userName');
    this.userName.next(userName);
  }
  getBearerToken() {
    return sessionStorage.getItem('myToken');
  }
  onLogout() {
    this.loggedIn.next(false);
  }
}
