package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;

import java.util.List;

public interface EventGalleryService {
	List<EventGalleryDTO> getLatestGalleryEvents(int count);
}
