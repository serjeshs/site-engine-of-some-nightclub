import { Component, OnInit } from '@angular/core';
import {UserDto} from "../../dto/userDto";
import {AuthService} from "../../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingInComponent implements OnInit {

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
