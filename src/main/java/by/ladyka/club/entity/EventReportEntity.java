package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "event_report")
@EntityListeners(AuditingEntityListener.class)
public class EventReportEntity extends AbstractEntity {
	private LocalDateTime startEvent;
	private String name;
	private String coverUri;
}
