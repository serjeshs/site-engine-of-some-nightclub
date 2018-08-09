import {Component, OnInit} from '@angular/core';
import {EventsService} from "../../events.service";
import {Event} from "../../dto/event";

@Component({
  selector: 'app-disco',
  templateUrl: './disco.component.html',
  styleUrls: ['./disco.component.css']
})
export class DiscoComponent implements OnInit {
  eventFirst: Event;
  eventSecond: Event;
  eventThird: Event;

  constructor(private eventService: EventsService) {
  }

  ngOnInit() {
    this.eventFirst = new Event();
    this.eventSecond = new Event();
    this.eventThird = new Event();

    this.eventService.getDiscoEvents()
      .subscribe(responce => {
        this.eventFirst = responce.items[0];
        this.eventSecond = responce.items[1];
        this.eventThird = responce.items[2];
      });
  }
}
