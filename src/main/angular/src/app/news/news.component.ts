import { Component, OnInit } from '@angular/core';
import {NewsService} from "../news.service";
import {NewsItemDto} from "../dto/newsItemDto";
import * as moment from "moment";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  news: NewsItemDto[];

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.buildNewsPage();
  }

  private buildNewsPage() {
    this.newsService.getNewsItems().subscribe(news => {
      this.news = news;
      this.news.forEach(newsItem => {
        newsItem.createDate = moment(newsItem.createDate).format('D MMMM YYYY');
      })
    });
  }
}
