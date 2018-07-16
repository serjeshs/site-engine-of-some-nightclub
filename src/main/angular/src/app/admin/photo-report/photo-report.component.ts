import {Component, OnInit} from '@angular/core';
import {EventReport} from "../../dto/eventGallery";
import {FileUploader} from "ng2-file-upload";
import {PhotoReportService} from "../../services/photo-report/photo-report.service";

class PhotoReportComponentWrapper {
  mode: string;
  reportEdit: EventReport;
  message: string;
}

@Component({
  selector: 'app-photo-report',
  templateUrl: './photo-report.component.html',
  styleUrls: ['./photo-report.component.css']
})
export class PhotoReportComponent implements OnInit {
  wrapper: PhotoReportComponentWrapper;
  uploader: FileUploader;

  constructor(private photoReportService: PhotoReportService) {
  }

  ngOnInit() {
    this.uploader = new FileUploader({url: '/api/file/upload', itemAlias: 'file'});
    this.wrapper = new PhotoReportComponentWrapper();
    this.wrapper.mode = 'tableView';
    this.wrapper.reportEdit = new EventReport();
    this.uploader.onAfterAddingFile = (file) => {
      file.withCredentials = false;
    };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log('ImageUpload:uploaded:', item, status, response);
      debugger;
      this.photoReportService.addFileToReport(JSON.parse(response).filePath, this.wrapper.reportEdit.id)
        .subscribe(responce => {
          if (responce.success) {
            alert('File uploaded successfully');
          } else {
            alert('File uploaded fail!');
            this.wrapper.message = 'FAIL UPLOAD IMAGE';
          }
        });
    };
    this.wrapper.message = '';
  }

  addNewPhotoReport() {
    this.wrapper.mode = 'editReport';
  }

  onClickSaveReport(reportEdit: EventReport) {
    this.wrapper.message = '';
    this.photoReportService.save(reportEdit)
      .subscribe(responce => {
        if (responce.success) {
          this.wrapper.reportEdit = responce.data;
          console.log(responce);
          console.log(this.wrapper.reportEdit);
        } else {
          this.wrapper.message = responce.message;
        }
      });
  }
}
