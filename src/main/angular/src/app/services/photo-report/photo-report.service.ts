import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {ResponseEntity} from "../../dto/ResponseEntity";
import {httpOptions} from "../../dto/httpOptions";
import {EventReport} from "../../dto/eventGallery";

@Injectable()
export class PhotoReportService {

  constructor(private http: HttpClient) {
  }


  save(report: EventReport): Observable<ResponseEntity> {
    let url = '/api/event/report';
    return this.http.post<ResponseEntity>(url, report, httpOptions).pipe(
      // catchError(this.handleError('authService.login', new ResponseEntity()))
    );
  }

  addFileToReport(filePath: string, id: number) {
    let url = '/api/event/report/file';
    let dto = {
      'filePath': filePath,
      'reportId' : id
    };
    return this.http.post<ResponseEntity>(url, dto, httpOptions).pipe(
      // catchError(this.handleError('authService.login', new ResponseEntity()))
    );
  }
}
