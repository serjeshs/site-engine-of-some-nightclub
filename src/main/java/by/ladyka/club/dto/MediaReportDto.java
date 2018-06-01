package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MediaReportDto {
	private Long id;
	//    @JsonFormat(pattern = Config.DATE_TIME_PATTERN)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startEvent;
	private String name;
	private String coverUri;
}
