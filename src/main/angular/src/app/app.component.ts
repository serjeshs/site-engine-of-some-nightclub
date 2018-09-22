import {Component, HostListener} from '@angular/core';
import {AuthService} from "./auth.service";
import {StatisticService} from "./services/statistic/statistic.service";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isAdmin: boolean;
  innerWidth: number;
  isProd: boolean = false;
  constructor(private authService: AuthService, private statisticService: StatisticService) {

  }

  ngOnInit() {
    this.onResize(null);
    this.authService.currentUser()
      .subscribe(user => {
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
    this.isProd = environment.production
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
  }
}
