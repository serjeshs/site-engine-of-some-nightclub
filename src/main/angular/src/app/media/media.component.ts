import {Component, OnInit} from '@angular/core';
import {EventReport} from "../dto/eventGallery";
import * as moment from "moment";
import {EventReportService} from "../event-report.service";

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.css']
})
export class MediaComponent implements OnInit {

  private galleryEvents: EventReport[];

  constructor(private eventReportService: EventReportService) {
  }

  ngOnInit() {
    this.buildMediaPage();
  }

  private buildMediaPage() {
    this.eventReportService.getReportEvents().subscribe(eventsReport => {
      this.galleryEvents = eventsReport;
      this.galleryEvents.forEach(eventsReport => {
        eventsReport.startEvent = moment(eventsReport.startEvent).format('D MMM YYYY');
        eventsReport.name = eventsReport.name.substring(0, 12);
      })
    });
  }
}
