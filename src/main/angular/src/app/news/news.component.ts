import { Component, OnInit } from '@angular/core';
import {NewsService} from "../news.service";
import {NewsItemDto} from "../dto/newsItemDto";

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
    });
  }
}
