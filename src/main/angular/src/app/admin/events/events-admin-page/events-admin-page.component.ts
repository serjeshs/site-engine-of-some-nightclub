import {Component, OnInit} from '@angular/core';
import {Event} from "../../../dto/event";
import {EventsService} from "../../../events.service";

@Component({
  selector: 'app-events-admin-page',
  templateUrl: './events-admin-page.component.html',
  styleUrls: ['./events-admin-page.component.css']
})
export class EventsAdminPageComponent implements OnInit {
  viewEvents: boolean;
  viewEvent: boolean;
  event: Event;

  constructor(private eventsService: EventsService) {
  }

  ngOnInit() {
    this.viewEvents = true;
    this.viewEvent = false;
    this.event = new Event();
  }

  handleRowSelected(row: Event) {
    this.event = row;
    this.viewEvent = true;
    this.viewEvents = false;
  }

  createNewEvent() {
    this.event = new Event();
    this.viewEvent = true;
    this.viewEvents = false;
  }

  saveEvent(event: Event) {
    this.eventsService.save(event).subscribe(event => {
      this.event = event;
    });
  }

  deleteEvent(eventId: number) {
    this.eventsService.deleteEvent(eventId)
      .subscribe(data=> {
        if (!!data.success) {
          alert("Удалено!");
          this.viewEvents = true;
          this.viewEvent = false;
          this.event = new Event();
        }
      });
  }
}
