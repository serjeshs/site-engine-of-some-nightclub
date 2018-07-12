import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {UserDto} from "../dto/userDto";

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent implements OnInit {
  passwordError: boolean;
  user: UserDto;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.passwordError = false;
    this.user = new UserDto();
  }

  singUpClick() {
    this.passwordError = false;
    this.authService.login(this.user.username, this.user.password)
      .subscribe(responce => {
        if (responce.success) {
          // this.router.navigate(['/']);
          document.location.href = '/';
        } else {
          this.passwordError = true;
        }
      })
  }
}
