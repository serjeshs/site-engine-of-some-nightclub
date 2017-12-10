import {Component, OnInit} from '@angular/core';
import {Event} from '../dto/event';
import {EventGallery} from "../dto/eventGallery";
import {EventRelevant} from "../dto/eventRelevant";

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

  constructor() {
  }

  ngOnInit() {
    this.getEvents();
    this.currentDate = new Date() + '';
    this.tomorrowDate = new Date() + '';
      // .format('m-d-Y h:i:s');
  }

  private getEvents() {
    let mainPage = {
      today: [],
      tomorrow: [],
      currentAndNextWeek: [],
      currentMonth: [],
      nextMonth: [],
      nextNexMonth: [],
      relevant: [],
      galery: []
    };

    this.today = mainPage.today;
    this.tomorrow = mainPage.tomorrow;
    this.currentAndNextWeek = mainPage.currentAndNextWeek;
    this.currentMonth = mainPage.currentMonth;
    this.nextMonth = mainPage.nextMonth;
    this.nextNextMonth = mainPage.nextNexMonth;
    this.relevant = mainPage.relevant;
    this.gallery = mainPage.galery;
  }
}
