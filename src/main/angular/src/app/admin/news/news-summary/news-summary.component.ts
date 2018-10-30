import {Component, OnInit} from '@angular/core';
import {NewsItemDto} from "../../../dto/newsItemDto";
import {NewsService} from "../../../news.service";

@Component({
  selector: 'app-news-summary',
  templateUrl: './news-summary.component.html',
  styleUrls: ['./news-summary.component.css']
})
export class NewsSummaryComponent implements OnInit {
  model: NewsItemDto;
  news: NewsItemDto[];
  mode: string;
  constructor(private newsService: NewsService) {
  }

  ngOnInit() {
    this.mode = 'view';
    this.model = new NewsItemDto();
    this.loadNews();
  }

  private loadNews() {
    this.newsService.getNewsItems().subscribe(news => {
      this.news = news;
    });
  }

  addNewsButton() {
    this.changeMode('edit', new NewsItemDto(), []);
  }

  editNews(newsItem: NewsItemDto) {
    this.changeMode('edit', newsItem, []);
  }

  private changeMode(mode: string, newsItem: NewsItemDto, news) {
    this.mode = mode;
    this.model = newsItem;
    this.news = news;
  }

  save() {
    this.newsService.saveNews(this.model).pipe().subscribe(news => {
      alert("Новость сохранена");
      this.model = news;
      this.loadNews();
      this.changeMode('view', new NewsItemDto(), []);

    });
  }
}
