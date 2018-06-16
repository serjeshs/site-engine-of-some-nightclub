import {Component, OnInit} from '@angular/core';
import {NewsService} from "../news.service";
import {NewsItemDto} from "../dto/newsItemDto";
import {ActivatedRoute, Params} from '@angular/router';
import * as moment from "moment";


@Component({
  selector: 'app-news-page',
  templateUrl: './news-page.component.html',
  styleUrls: ['./news-page.component.css']
})
export class NewsPageComponent implements OnInit {
  private newsItem: NewsItemDto;
  private id: string;

  constructor(private newsService: NewsService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.buildNewsItemPage();
  }

  private buildNewsItemPage() {
    this.newsService.getNewsItem(this.id).subscribe(newsItem => {
      this.newsItem = newsItem;
      this.newsItem.createDate = moment(newsItem.createDate).format('D MMMM YYYY');
    });
  }
}
