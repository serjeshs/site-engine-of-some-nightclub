import {Component, OnInit} from '@angular/core';
import {EventsService} from "../events.service";
import {Event} from "../dto/event";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-event-page',
  templateUrl: './event-page.component.html',
  styleUrls: ['./event-page.component.css']
})
export class EventPageComponent implements OnInit {
  private eventService: EventsService;
  event: Event;

  constructor(private activatedRoute: ActivatedRoute, eventService: EventsService) {
    this.eventService = eventService;
  }

  ngOnInit() {
    this.event = new Event();
    let id = +this.activatedRoute.snapshot.paramMap.get('id');
    this.eventService.getEvent(id)
      .subscribe(responce => {
        this.event = responce;
      });
  }

}
