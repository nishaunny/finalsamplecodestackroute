import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthenticationService } from './authentication.service';
@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private route: Router, private location: Location) { }

  routeToSchedules() {
    this.route.navigate(['upcoming']);
  }

  routeBack() {
    this.location.back();
  }

  routeToHome() {
    this.route.navigate(['live']);
  }
  routeToLogin() {
    this.route.navigate(['login']);
  }
  onLogout() {
    sessionStorage.removeItem('myToken');
    sessionStorage.removeItem('userName');
    this.routeToLogin();
  }
}
