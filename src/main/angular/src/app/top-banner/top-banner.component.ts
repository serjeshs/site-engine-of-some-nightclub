import { Component, OnInit } from '@angular/core';
import {SettingsBanner} from "../dto/settingsBanner";

@Component({
  selector: 'app-top-banner',
  templateUrl: './top-banner.component.html',
  styleUrls: ['./top-banner.component.css']
})
export class TopBannerComponent implements OnInit {
  settingsBanner: SettingsBanner;

  constructor() { }

  ngOnInit() {
    this.settingsBanner = new SettingsBanner();
    this.settingsBanner.eventId = 1113;
    this.settingsBanner.eventName = 'HАРРУ NEW YEAR';
    this.settingsBanner.imageUrl = 'https://republic-club.by/files/2018/11/banner.jpg';
    this.settingsBanner.startTime = '31 декабря 2018 г., 22:00'
  }

}
