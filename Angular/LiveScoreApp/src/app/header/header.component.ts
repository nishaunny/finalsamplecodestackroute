import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../shared/services/authentication.service';
import { RouteService } from '../shared/services/route.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$!: Observable<boolean>;
  userName!: string;
  constructor(public authService: AuthenticationService, private routeService: RouteService) {
  }

  ngOnInit(): void {
    this.isLoggedIn$ = this.authService.isUserLoggedIn;
    if (this.isLoggedIn$) {
      this.authService.user.subscribe(name => {
        this.userName = name;
      });
    }
  }

  logout() {
    this.authService.onLogout();
    this.routeService.onLogout();
  }
}
