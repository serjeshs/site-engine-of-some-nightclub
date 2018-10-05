package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class EventDTO {
	private Long id;
	private BigDecimal cost;
	private String costText;
	private String description;

	//    @JsonFormat(pattern = Config.DATE_TIME_PATTERN)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startEvent;

	//    @JsonFormat(pattern = Config.DATE_TIME_PATTERN)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime endEvent;

	private String name;
	private String coverUri;
	private String buyTicketUrl;
	private Boolean recommendation;
	private Boolean republicPay;
}
