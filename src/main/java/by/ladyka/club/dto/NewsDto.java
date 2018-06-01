package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewsDto {
	private Long id;
	private String title;
	private String descriptionPreview;
	private String description;
	private String owner;
	private String image;
	private LocalDateTime createDate;
}
