import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PhotoReportService} from "../../../services/photo-report/photo-report.service";
import {PreSummaryWrapper} from "../../../dto/preSummaryWrapper";
import {EventReportService} from "../../../event-report.service";
import {FileUploader} from "ng2-file-upload";

@Component({
  selector: 'app-pre-report-editor',
  templateUrl: './pre-report-editor.component.html',
  styleUrls: ['./pre-report-editor.component.css'],
})
export class PreReportEditorComponent implements OnInit {
  @Output() onCloseButtonClick = new EventEmitter();
  @Input() dataGallery: PreSummaryWrapper = new PreSummaryWrapper();
  uploader: FileUploader;
  private message: string;

  constructor(private photoReportService: PhotoReportService, private eventReportService: EventReportService) {
  }

  ngOnInit() {
    this.uploader = new FileUploader({url: '/api/file/upload', itemAlias: 'file'});
    this.uploader.onAfterAddingFile = (file) => {
      file.withCredentials = false;
      console.log(file);
    };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log('ImageUpload:uploaded:', item, status, response);
      this.photoReportService.addFileToReport(JSON.parse(response).filePath, this.dataGallery.report.id)
        .subscribe(responce => {
          if (responce.success) {
            alert('File uploaded successfully');
          } else {
            alert('File uploaded fail!');
            this.message = 'FAIL UPLOAD IMAGE';
          }
        });
    };
    this.message = '';
    this.eventReportService.getReportEvent(this.dataGallery.report.id)
      .subscribe(eventReport => {
        this.dataGallery.report = eventReport;
        if (!this.dataGallery.report.images) {
          this.dataGallery.report.images = [];
        }
      });
  }

  closeReport() {
    this.onCloseButtonClick.emit();
  }

  onClickSaveReport() {
    console.log("NOT impl");
  }
}
