import {Injectable} from '@angular/core';
import {FeedBackDto} from "./dto/FeedBackDto";
import {Observable} from "rxjs/index";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class FeedbackService {
  private feedBackUrl: string;

  constructor(private http: HttpClient) {
    this.feedBackUrl = "/api/feedback";
  }

  sendFeedBack(model: FeedBackDto): Observable<FeedBackDto> {
    return this.http.post<FeedBackDto>(this.feedBackUrl, JSON.stringify(model), httpOptions).pipe();
  }
}
