package by.ladyka.club.service;

import by.ladyka.club.entity.old.ModxSiteContent;
import by.ladyka.club.repository.old.ModxSiteContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ModxServiceImpl implements ModxService {
	private final ModxSiteContentRepository modxSiteContentRepository;

	@Autowired
	public ModxServiceImpl(ModxSiteContentRepository modxSiteContentRepository) {
		this.modxSiteContentRepository = modxSiteContentRepository;
	}

	@Override
	public List<ModxSiteContent> getEventsBetween(LocalDateTime after, LocalDateTime before) {
		return modxSiteContentRepository.findAll();
	}
}
