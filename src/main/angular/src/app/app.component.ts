import {Component, HostListener} from '@angular/core';
import {AuthService} from "./auth.service";
import {StatisticService} from "./services/statistic/statistic.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isAdmin: boolean;
  innerWidth: number;
  isUser: boolean;
  isConcert: boolean;

  constructor(private authService: AuthService, private statisticService: StatisticService) {

  }

  ngOnInit() {
    this.onResize(null);
    this.authService.currentUser()
      .subscribe(user => {
        this.isUser = (('admin' == user.role) || ('concert' == user.role) || ('user' == user.role))
        this.isConcert = ('concertManager' == user.role);
        this.isAdmin = ('admin' == user.role);
      });
    const loader = document.getElementsByClassName('lds-roller')[0];
    loader.parentElement.removeChild(loader);
    const indexHtmlLoaded = window['indexHtmlLoaded'];
    const currentDate = new Date();
    const seconds = (currentDate.getTime() - indexHtmlLoaded.getTime());
    this.statisticService.storeLoadWebAppData(seconds)
      .pipe()
      .subscribe(response => {
        console.log(response);
      });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
  }
}
