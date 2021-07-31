import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../shared/services/authentication.service';
import { RouteService } from '../shared/services/route.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user!: FormGroup;
  submitMessage: string;
  token: string;
  registerUser!: FormGroup;
  constructor(private authservice: AuthenticationService, private routeService: RouteService, private snackBar: MatSnackBar) {
   
    this.submitMessage = "";
    this.token = "";
  }

  ngOnInit(): void {
    this.user = new FormGroup({
      username: new FormControl('',[Validators.required]),
      password: new FormControl('', [Validators.required,Validators.minLength(6)])
    });
    this.registerUser = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.minLength(6)),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      cPassword: new FormControl('', Validators.minLength(6)),
    });
  }
  onSubmit() {
    var mydata = this.user.value;
    this.authservice.authenticateUser(mydata).subscribe(
      (res: any) => {
        this.token = res['token'];
        console.log(this.token)
        this.authservice.setBearerToken(this.token,this.user.value.username);
        this.routeService.routeToHome();
      },
      (err) => {
        this.submitMessage = err.message;
        if (err.status === 401) {
          
        this.snackBar.open( 'User does not exist', '', { duration: 2000 ,verticalPosition: 'top', panelClass: ['red-snackbar']});
        } else {
          this.snackBar.open( '404 Not Found', '', { duration: 2000 ,verticalPosition: 'top', panelClass: ['red-snackbar']});
        }
      }

    );
  }
  registerSubmit() {
    let mydata = this.registerUser.value;
    this.authservice.adduser(mydata).subscribe(res =>{ console.log(res);
          
      this.snackBar.open( 'Registered Successfully', '', { duration: 2000 ,verticalPosition: 'top', panelClass: ['green-snackbar']})
    },
      (err) => {
        this.submitMessage = err.message;
            
        this.snackBar.open(this.submitMessage, '', { duration: 2000 ,verticalPosition: 'top', panelClass: ['red-snackbar']});
    });
    this.registerUser.reset();
    this.registerUser.markAsUntouched();
  }
}
