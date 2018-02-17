import {Component, OnInit} from '@angular/core';
import {EventReport} from "../dto/eventGallery";
import {EventsService} from "../events.service";
import * as moment from "moment";

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.css']
})
export class MediaComponent implements OnInit {

  private galleryEvents: EventReport[];

  constructor(private eventService: EventsService) {
  }

  ngOnInit() {
    this.buildMediaPage();
  }

  private buildMediaPage() {
    this.eventService.getReportEvents().subscribe(eventsReport => {
      this.galleryEvents = eventsReport;
      this.galleryEvents.forEach(eventsReport=> {
        eventsReport.startEvent = moment(eventsReport.startEvent).format('D MMM YYYY');
        eventsReport.name = eventsReport.name.substring(0, 12);
      })
    });
  }
}
