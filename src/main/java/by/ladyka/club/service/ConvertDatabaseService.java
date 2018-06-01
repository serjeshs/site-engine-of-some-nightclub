package by.ladyka.club.service;

import by.ladyka.club.EventStatus;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.entity.NewsEntity;
import by.ladyka.club.entity.UserEntity;
import by.ladyka.club.entity.old.ModxSiteContent;
import by.ladyka.club.entity.old.ModxSiteTmplVarContentValues;
import by.ladyka.club.entity.old.ModxSiteTmplVars;
import by.ladyka.club.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import static by.ladyka.club.EventStatus.*;
import static by.ladyka.club.entity.old.ModxSiteTmplVars.*;

@Service
public class ConvertDatabaseService {

	private final static Logger logger = LoggerFactory.getLogger(ConvertDatabaseService.class);
	private final ModxSiteContentRepository modxSiteContentRepository;
	private final EventRepository eventRepository;
	private final NewsEntityRepository newsEntityRepository;
	private final UserEntityRepository userEntityRepository;
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private final DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm:ss");
	private final EventReportRepository eventReportRepository;
	private final MenuService menuService;

	@Autowired
	public ConvertDatabaseService(ModxSiteContentRepository modxSiteContentRepository, EventRepository eventRepository, NewsEntityRepository newsEntityRepository, UserEntityRepository userEntityRepository, EventReportRepository eventReportRepository, MenuService menuService) {
		this.modxSiteContentRepository = modxSiteContentRepository;
		this.eventRepository = eventRepository;
		this.newsEntityRepository = newsEntityRepository;
		this.userEntityRepository = userEntityRepository;
		this.eventReportRepository = eventReportRepository;
		this.menuService = menuService;
	}


	public Boolean convertDataBase() {
		try {
			if (userEntityRepository.findAll().size() < 1) {
				for (long i = 1L; i < 20L; i++) {
					UserEntity five = UserEntity.unconfirmUser();
					five.setId(i);
					userEntityRepository.save(five);
				}
			} else {
				final int events = (int) eventRepository.count();
				logger.info("Events in database : " + events);
				final int eventsReports = (int) eventReportRepository.count();
				logger.info("Events in database : " + events);
				final int news = (int) newsEntityRepository.count();
				logger.info("News in database : " + news);
				modxSiteContentRepository.findAll().forEach(modxSiteContent -> {
					long template = modxSiteContent.getTemplate();
					switch ((int) template) {
						case 7: {
							if (events == 0) {
								convertAndSaveEvent(modxSiteContent);
							}
						}
						break;
						case 9: {
							if (eventsReports == 0) {
								convertAndSaveEventReport(modxSiteContent);
							}
						}
						break;
						case 10: {
							if (news == 0) {
								convertAndSaveNews(modxSiteContent);
							}
							break;
						}
					}
				});
				menuService.init();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	private void convertAndSaveEventReport(ModxSiteContent modxSiteContent) {
		final List<ModxSiteTmplVarContentValues> contentValues = modxSiteContent.getContentValues();
		EventReportEntity entity = new EventReportEntity();
		entity.setId(modxSiteContent.getId());
		entity.setCoverUri("http://republic-club.by/" + get(contentValues, img_));
		entity.setName(modxSiteContent.getPagetitle());
		entity.setStartEvent(toLDT(get(contentValues, event_date)));
		entity.setVisible(Boolean.TRUE);
		try {
			eventReportRepository.save(entity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void convertAndSaveNews(ModxSiteContent modxSiteContent) {
		final List<ModxSiteTmplVarContentValues> contentValues = modxSiteContent.getContentValues();
		NewsEntity entity = new NewsEntity();
		entity.setId(modxSiteContent.getId());
		entity.setTitle(modxSiteContent.getPagetitle());
		entity.setDescriptionPreview(modxSiteContent.getIntrotext());
		entity.setDescription(modxSiteContent.getContent());
		entity.setAlias(modxSiteContent.getAlias());
		UserEntity owner = userEntityRepository.getOne(modxSiteContent.getCreatedby());
		entity.setOwner(owner);
		entity.setImage("http://republic-club.by/" + get(contentValues, news_img));
		entity.setVisible(Boolean.TRUE);
		try {
			newsEntityRepository.save(entity);
			Long createdon = modxSiteContent.getCreatedon();
			createdon *= 1000;
			final Timestamp timestamp = new Timestamp(createdon);
			entity.setCreatedDate(timestamp.toLocalDateTime());
			newsEntityRepository.save(entity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void convertAndSaveEvent(ModxSiteContent modxSiteContent) {
		final List<ModxSiteTmplVarContentValues> contentValues = modxSiteContent.getContentValues();
		Event event = new Event();
		event.setId(modxSiteContent.getId());
		event.setCost(new BigDecimal(0));
		event.setCostText(get(contentValues, price));
		event.setCoverUri("http://republic-club.by/" + get(contentValues, img_));
		event.setName(modxSiteContent.getPagetitle());
		event.setDescription(modxSiteContent.getContent());
		event.setStartEvent(toLDT(get(contentValues, event_date)));
		event.setEndEvent(toLDT(get(contentValues, event_end_date)));
		event.setStatus(getStatus(event.getStartEvent()).getCode());
		event.setAlias(modxSiteContent.getAlias());
		try {
			eventRepository.save(event);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private EventStatus getStatus(LocalDateTime startEvent) {
		if (startEvent == null) {
			return DRAFT;
		}
		int c = LocalDateTime.now().compareTo(startEvent);
		return (c < 0) ? DONE : PENDING;
	}

	private LocalDateTime toLDT(String s) {

		if (s == null) {
			return null;
		}
		try {
			return LocalDateTime.parse(s, dateTimeFormatter);
		} catch (DateTimeParseException ex) {
			try {
				return LocalDateTime.parse(s, dateTimeFormatterShort);
			} catch (DateTimeParseException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	private String get(List<ModxSiteTmplVarContentValues> contentValues, ModxSiteTmplVars var) {
		return contentValues
				.stream()
				.filter(contentValue -> Objects.equals(contentValue.getTmplvarid(), var.getId()))
				.findFirst()
				.map(ModxSiteTmplVarContentValues::getValue)
				.orElse(null);
	}
}
