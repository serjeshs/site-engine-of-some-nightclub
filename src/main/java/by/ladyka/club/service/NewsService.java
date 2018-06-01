package by.ladyka.club.service;

import by.ladyka.club.dto.NewsDto;

import java.util.List;

public interface NewsService {
	List<NewsDto> summary();

	NewsDto byId(Long id);
}
