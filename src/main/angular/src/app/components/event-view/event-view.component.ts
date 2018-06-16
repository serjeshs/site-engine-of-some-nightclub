import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from "../../dto/event";

@Component({
  selector: 'app-event-view',
  templateUrl: './event-view.component.html',
  styleUrls: ['./event-view.component.css']
})
export class EventViewComponent implements OnInit {
  viewEvent: boolean;
  @Output() onEventSaveButtonClick = new EventEmitter();
  @Input() private event: Event = new Event();
  @Input() private readOnly: boolean;

  constructor() {

  }

  ngOnInit() {
    this.viewEvent = true;
  }

  setViewEvent(b: boolean) {
    this.viewEvent = b;
  }

  saveEvent(event: Event) {
    this.onEventSaveButtonClick.emit(event);
  }
}
