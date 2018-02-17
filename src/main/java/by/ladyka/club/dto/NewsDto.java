package by.ladyka.club.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
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
