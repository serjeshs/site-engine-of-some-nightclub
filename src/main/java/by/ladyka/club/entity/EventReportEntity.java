package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

	@OneToMany(mappedBy = "eventReport")
	List<EventReportImageEntity> images;
}
