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
  displayedColumns = ['id', 'name', 'startEvent', 'status'];
  @Output() onRowSelected = new EventEmitter();

  constructor(private eventsService: EventsService) {
  }

  ngOnInit() {
    this.loadEvents(0);
  }

  private loadEvents(page: number) {
    this.eventsService.getEvents(page).subscribe(eventsResponse => {
      this.events = eventsResponse;
    });
  }

  handleRowClick(row: Event) {
    console.log("handleRowClick", row);
    console.log(this.onRowSelected);
    this.onRowSelected.emit(row);
  }
}
