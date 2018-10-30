import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PhotoReportService} from "../../../services/photo-report/photo-report.service";
import {PreSummaryWrapper} from "../../../dto/preSummaryWrapper";
import {EventReportService} from "../../../event-report.service";
import {humanizeBytes, UploaderOptions, UploadFile, UploadInput, UploadOutput} from 'ngx-uploader';


@Component({
  selector: 'app-pre-report-editor',
  templateUrl: './pre-report-editor.component.html',
  styleUrls: ['./pre-report-editor.component.css'],
})
export class PreReportEditorComponent implements OnInit {
  @Output() onCloseButtonClick = new EventEmitter();
  @Input() dataGallery: PreSummaryWrapper = new PreSummaryWrapper();
  private message: string;
  options: UploaderOptions;
  files: UploadFile[];
  uploadInput: EventEmitter<UploadInput>;
  humanizeBytes: Function;
  dragOver: boolean;
  private coverUri: string;

  constructor(private photoReportService: PhotoReportService, private eventReportService: EventReportService) {
    this.options = {concurrency: 1, maxUploads: 3};
    this.files = []; // local uploading files array
    this.uploadInput = new EventEmitter<UploadInput>(); // input events, we use this to emit data to ngx-uploader
    this.humanizeBytes = humanizeBytes;
  }

  ngOnInit() {
    this.message = '';
    this.loadData();
  }

  private loadData() {
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
    this.dataGallery.report.coverUri = this.coverUri;
    this.photoReportService.save(this.dataGallery.report)
      .subscribe(responce => {
        if (responce.success) {
          this.dataGallery.report = responce.data;
        } else {
          alert(responce.message);
        }
      });
  }


  onUploadOutput(output: UploadOutput): void {

    if (output.type === 'allAddedToQueue') { // when all files added in queue
      // uncomment this if you want to auto upload files when added
      // const event: UploadInput = {
      //   type: 'uploadAll',
      //   url: '/upload',
      //   method: 'POST',
      //   data: { foo: 'bar' }
      // };
      // this.uploadInput.emit(event);
    } else if (output.type === 'addedToQueue' && typeof output.file !== 'undefined') { // add file to array when added
      this.files.push(output.file);
    } else if (output.type === 'uploading' && typeof output.file !== 'undefined') {
      // update current data in files array for uploading file
      const index = this.files.findIndex(file => typeof output.file !== 'undefined' && file.id === output.file.id);
      this.files[index] = output.file;
    } else if (output.type === 'removed') {
      // remove file from array when removed
      this.files = this.files.filter((file: UploadFile) => file !== output.file);
    } else if (output.type === 'dragOver') {
      this.dragOver = true;
    } else if (output.type === 'dragOut') {
      this.dragOver = false;
    } else if (output.type === 'drop') {
      this.dragOver = false;
    }

    switch (output.type) {
      case 'done' : {
        const response = output.file.response;
        this.photoReportService.addFileToReport(response.filePath, this.dataGallery.report.id)
          .subscribe(responce => {
            if (responce.success) {
              alert('File uploaded successfully');
            } else {
              alert('File uploaded fail!');
            }
            this.loadData();
            document.getElementById('newPhoto')['value'] = null;
          });
      }
        break;
      default : {
        console.log(output.type);
      }
    }
  }

  startUpload(): void {
    const event: UploadInput = {
      type: 'uploadAll',
      url: '/api/file/upload',
      method: 'POST',
      data: {foo: 'bar'}
    };

    this.uploadInput.emit(event);
  }

  cancelUpload(id: string): void {
    this.uploadInput.emit({type: 'cancel', id: id});
  }

  removeFile(id: string): void {
    this.uploadInput.emit({type: 'remove', id: id});
  }

  removeAllFiles(): void {
    this.uploadInput.emit({type: 'removeAll'});
  }

  isChecked(imageUri: string) {
    return (this.dataGallery.report.coverUri == imageUri);
  }


  handleChangeCoverUri(evt, imageUri) {
    if (evt.target.checked) {
      this.coverUri = imageUri;
    }
  }
}
