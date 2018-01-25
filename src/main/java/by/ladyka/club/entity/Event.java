package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "event")
@EntityListeners(AuditingEntityListener.class)
public class Event extends AbstractEntity{
    private BigDecimal costMinimum;
    @Lob
    private String costText;
    @Lob
    private String description;
    private LocalDateTime startEvent;
    private LocalDateTime endEvent;
    private String name;
    private String coverUri;
    private int status;
}
