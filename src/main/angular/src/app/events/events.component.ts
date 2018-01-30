import {Component, OnInit} from '@angular/core';
import {Event} from '../dto/event';
import {EventGallery} from "../dto/eventGallery";
import {EventRelevant} from "../dto/eventRelevant";
import {EventsService} from "../events.service";
import * as moment from "moment";
import 'moment/locale/ru';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  today: Event[];
  tomorrow: Event[];
  currentAndNextWeek: Event[];
  currentMonth: Event[];
  nextMonth: Event[];
  nextNextMonth: Event[];
  relevant: EventRelevant[];
  gallery: EventGallery[];
  currentDate: string;
  tomorrowDate: string;
  currentMonthName: string;
  nextNextMonthName: string;
  nextMonthName: string;

  constructor(private eventsService: EventsService) {
  }

  ngOnInit() {
    this.getEvents();
    this.getMonthNames();
  }

  private getMonthNames() {
    let dateFormatOptions = {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    };
    let todayDate = new Date();
    this.currentDate = todayDate.toLocaleString("ru", dateFormatOptions);
    let tomorrowDate = todayDate;
    tomorrowDate.setDate(tomorrowDate.getDate() + 1);
    this.tomorrowDate = tomorrowDate.toLocaleString("ru", dateFormatOptions);
    this.currentMonthName = moment().format('D MMMM');
    // this.currentMonthName = todayDate.toLocaleDateString("ru", {month: 'long'});
    let nextMonthDate = new Date();
    nextMonthDate.setMonth(nextMonthDate.getMonth() + 1);
    this.nextMonthName = nextMonthDate.toLocaleDateString("ru", {month: 'long'});
    let nextNextMonthDate = new Date();
    nextNextMonthDate.setMonth(nextNextMonthDate.getMonth() + 2);
    this.nextNextMonthName = nextNextMonthDate.toLocaleDateString("ru", {month: 'long'});
  }

  private getEvents() {
    this.eventsService.getEvents()
      .subscribe(mainPage => {
        this.today = mainPage['today'];
        this.tomorrow = mainPage['tomorrow'];
        this.currentAndNextWeek = mainPage['currentAndNextWeek'];
        this.currentMonth = mainPage['currentMonth'];
        this.nextMonth = mainPage['nextMonth'];
        this.nextNextMonth = mainPage['nextNextMonth'];
        this.relevant = mainPage['relevant'];
        this.gallery = mainPage['gallery'];
      });


  }
}
