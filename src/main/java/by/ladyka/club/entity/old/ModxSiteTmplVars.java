package by.ladyka.club.entity.old;

public enum ModxSiteTmplVars {
	blogContent(1L, "blogContent"),
	loginName(2L, "loginName"),
	documentTags(3L, "Tags"),
	event_date_unix(4L, "Дата мероприятия"),
	event_date(5L, "Дата мероприятия"),
	event_end_date(6L, "Дата окончания мероприятия"),
	event_type(7L, "Тип мероприятия"),
	img_(8L, "картинка для анонса"),
	price(9L, "Стоимость билета"),
	gallery(12L, "фото отчет"),
	gallery2(11L, "фото отчет"),
	news_img(14L, "картинка для новости"),
	sitemap_changefreq(15L, "sitemap_changefreq"),
	sitemap_priority(16L, "sitemap_priority"),
	keywords(17L, "ключевые слова"),
	title_(18L, "тэг TITLE"),
	kassir_code(19L, "Код мероприятия");

	private Long id;
	private String description;

	ModxSiteTmplVars(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
