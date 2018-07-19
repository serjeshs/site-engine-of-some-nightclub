import {Component, HostListener, OnInit} from '@angular/core';
import {Event} from '../dto/event';
import {EventReport} from "../dto/eventGallery";
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
  gallery: EventReport[];
  currentDate: string;
  tomorrowDate: string;
  currentMonthName: string;
  nextNextMonthName: string;
  nextMonthName: string;
  innerWidth: number;

  constructor(private eventsService: EventsService) {
  }

  ngOnInit() {
    this.innerWidth = window.window.innerWidth;
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
    this.currentMonthName = moment().format('MMMM');
    // this.currentMonthName = todayDate.toLocaleDateString("ru", {month: 'long'});
    let nextMonthDate = new Date();
    nextMonthDate.setMonth(nextMonthDate.getMonth() + 1);
    this.nextMonthName = nextMonthDate.toLocaleDateString("ru", {month: 'long'});
    let nextNextMonthDate = new Date();
    nextNextMonthDate.setMonth(nextNextMonthDate.getMonth() + 2);
    this.nextNextMonthName = "Позже";
  }

  private getEvents() {
    function processMonthEvents(event) {
      event.startEvent = moment(event.startEvent).format('D MMMM');
      event.endEvent = moment(event.endEvent).format('dddd');
    }

    function processDayEvent(event) {
      event.startEvent = moment(event.startEvent).format('H:mm');
    }

    this.eventsService.getMainPageEvents()
      .subscribe(mainPage => {

        this.today = mainPage['today'];
        this.today.forEach(processDayEvent);
        this.tomorrow = mainPage['tomorrow'];
        this.tomorrow.forEach(processDayEvent);

        this.currentAndNextWeek = mainPage['currentAndNextWeek'];
        this.currentAndNextWeek.forEach(event => {
          event.startEvent = moment(event.startEvent).format('dddd[,] LL[,] LT')
        });

        this.currentMonth = mainPage['currentMonth'];
        this.currentMonth.forEach(processMonthEvents);
        this.nextMonth = mainPage['nextMonth'];
        this.nextMonth.forEach(processMonthEvents);
        this.nextNextMonth = mainPage['nextNextMonth'];
        this.nextNextMonth.forEach(processMonthEvents);


        this.gallery = mainPage['gallery'];
        this.gallery.forEach(eventGalery => {
          eventGalery.startEvent = moment(eventGalery.startEvent).format('LL')
        });

        this.relevant = mainPage['relevant'];
        this.relevant.forEach(event => {
          event.month = moment(event.startEvent).format('MMMM');
          event.date = moment(event.startEvent).format('D');
          event.day = moment(event.startEvent).format('dddd');
          event.time = moment(event.startEvent).format('H:mm');
        });
      });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
  }
}
