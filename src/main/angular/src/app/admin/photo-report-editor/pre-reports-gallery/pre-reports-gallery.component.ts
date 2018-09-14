import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EventReport} from "../../../dto/eventGallery";
import {PreSummaryWrapper} from "../../../dto/preSummaryWrapper";

@Component({
  selector: 'app-pre-reports-gallery',
  templateUrl: './pre-reports-gallery.component.html',
  styleUrls: ['./pre-reports-gallery.component.css']
})
export class PreReportsGalleryComponent implements OnInit {

  @Input() dataGallery : PreSummaryWrapper = new PreSummaryWrapper();
  @Output() onClickEditReport = new EventEmitter();
  constructor() {
  }

  ngOnInit() {
  }

  editReport(report: EventReport) {
    this.dataGallery.report = report;
    this.dataGallery.report.images = [];
    this.onClickEditReport.emit();
  }
}
