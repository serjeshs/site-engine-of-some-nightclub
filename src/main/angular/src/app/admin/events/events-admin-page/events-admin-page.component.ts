import {Component, OnInit} from '@angular/core';
import {Event} from "../../../dto/event";

@Component({
  selector: 'app-events-admin-page',
  templateUrl: './events-admin-page.component.html',
  styleUrls: ['./events-admin-page.component.css']
})
export class EventsAdminPageComponent implements OnInit {
  viewEvents: boolean;
  viewEvent: boolean;
  event: Event;

  constructor() {
  }

  ngOnInit() {
    this.viewEvents = true;
    this.viewEvent = false;
    this.event = new Event();
  }

  handleRowSelected(row: Event) {
    console.log("handleRowSelected", row);
    this.event = row;
    this.viewEvent = true;
    this.viewEvents = false;
  }

  createNewEvent() {
    this.event = new Event();
    this.viewEvent = true;
    this.viewEvents = false;
  }
}
