package by.ladyka.club.service;


import by.ladyka.club.entity.old.ModxSiteContent;

import java.time.LocalDateTime;
import java.util.List;

public interface ModxService {
	List<ModxSiteContent> getEventsBetween(LocalDateTime after, LocalDateTime before);
}
