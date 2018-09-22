import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {httpOptions} from "../../dto/httpOptions";
import {ResponseEntity} from "../../dto/ResponseEntity";

let getUserInformation = function () {
  return {
        appCodeName   : navigator.appCodeName   ,
        appName       : navigator.appName       ,
        appVersion    : navigator.appVersion    ,
        cookieEnabled : navigator.cookieEnabled ,
        language      : navigator.language      ,
        onLine        : navigator.onLine        ,
        platform      : navigator.platform      ,
        userAgent     : navigator.userAgent     ,
  };
};

@Injectable()
export class StatisticService {

  constructor(private http: HttpClient) {

  }

  storeLoadWebAppData(seconds: number) {
    let url = '/api/statistic';
    let data = getUserInformation();
    data['seconds'] = seconds;
    return this.http.post<ResponseEntity>(url, data, httpOptions);
  }
}
