import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventReportService} from "../event-report.service";
import {EventReport} from "../dto/eventGallery";
import * as moment from "moment";

@Component({
  selector: 'app-event-report',
  templateUrl: './event-report.component.html',
  styleUrls: ['./event-report.component.css']
})
export class EventReportComponent implements OnInit {
  eventReport: EventReport;
  photos: number;
  constructor(private activatedRoute: ActivatedRoute, private eventReportService: EventReportService) {
  }

  ngOnInit() {
    this.eventReport = new EventReport();
    let id = +this.activatedRoute.snapshot.paramMap.get('id');
    this.eventReportService.getReportEvent(id)
      .subscribe(eventReport => {
        this.eventReport = eventReport;
        this.eventReport.startEvent = moment(this.eventReport.startEvent).format('D MMM YYYY');
        this.photos = this.eventReport.images.length;
      });
  }

}
