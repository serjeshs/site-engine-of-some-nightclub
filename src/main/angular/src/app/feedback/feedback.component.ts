import {Component, OnInit} from '@angular/core';
import {MenuOrder} from "../dto/menuOrder";
import {FeedbackService} from "../feedback.service";
import {FeedBackDto} from "../dto/FeedBackDto";

class FeedBackWrapper {
  edited: boolean;
}

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  model = new FeedBackDto();
  wrapper = new FeedBackWrapper();
  constructor(private feedBackService: FeedbackService) {
  }

  ngOnInit() {
    this.wrapper.edited = true;
  }

  order() {
      this.feedBackService.sendFeedBack(this.model)
        .subscribe(response => {
          this.model = <FeedBackDto>response;
          this.wrapper.edited = false;
        });
  }

}
