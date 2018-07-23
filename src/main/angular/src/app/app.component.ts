import {Component, HostListener} from '@angular/core';
import {AuthService} from "./auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isAdmin: boolean;
  innerWidth: number;

  constructor(private authService: AuthService) {

  }

  ngOnInit() {
    this.onResize(null);
    this.authService.currentUser()
      .subscribe(user => {
        this.isAdmin= ('admin' == user.role);
      });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
  }
}
