import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  loginForm: FormGroup;
  constructor(private authService: AuthService, private router: Router) { }
  submitted = false;
  errorMessage = '';
  isLoggedin = false;
  isLoginFailed = false;
  ngOnInit() {
    this.loginForm = new FormGroup({
      userName: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
    });
  }
  onSubmit() {
    this.submitted = true;
    this.authService.login(this.loginForm.value.userName, this.loginForm.value.password).subscribe(
      data => {
        this.isLoggedin = true;
        console.log('login successfully');
        this.router.navigate(['']);
      },
      error => {
        console.log(error);
        this.errorMessage = error;
        this.isLoggedin = false;
        this.isLoginFailed = true;
      }
    );
  }
}
