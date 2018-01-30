package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventGalleryDTO {
    private Long id;
    private LocalDateTime startEvent;
    private String name;
    private String coverUri;
}
