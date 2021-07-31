import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './shared/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TokeInterceptorService implements HttpInterceptor{

  constructor(private authService:AuthenticationService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${this.authService.getBearerToken()}`
      }
    });
    return next.handle(req);
  }
}
