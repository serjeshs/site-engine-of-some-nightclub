import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EventsService} from "../../../events.service";
import {Event} from "../../../dto/event";

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit {
  events: Event[];
  displayedColumns = ['id', 'name', 'startEvent'];
  @Output() onRowSelected = new EventEmitter();

  constructor(private eventsService: EventsService) {
  }

  ngOnInit() {
    this.loadEvents(0);
  }

  handleRowClick(row: Event) {
    this.onRowSelected.emit(row);
  }

  private loadEvents(page: number) {
    this.eventsService.getEvents(page).subscribe(eventsResponse => {
      this.events = eventsResponse;
    });
  }
}
