import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../../dto/event";
import {EventsService} from "../../../events.service";

@Component({
  selector: 'app-event-view',
  templateUrl: './event-view.component.html',
  styleUrls: ['./event-view.component.css']
})
export class EventViewComponent implements OnInit {
  viewEvent: boolean;
  @Input() private event: Event = new Event();
  private eventService: EventsService;
  private message: string;

  constructor(eventService : EventsService) {
    this.eventService = eventService;
  }

  ngOnInit() {
    this.viewEvent = true;
  }

  setViewEvent(b: boolean) {
    this.viewEvent = b;
  }

  saveEvent(event: Event) {
    this.eventService.save(event).subscribe(event=>{
      this.event = event;
      this.message = "Event saved";
    });
  }
}
