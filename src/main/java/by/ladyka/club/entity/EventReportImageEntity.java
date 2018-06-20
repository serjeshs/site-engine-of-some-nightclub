package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "event_report_image")
@EntityListeners(AuditingEntityListener.class)
public class EventReportImageEntity extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "event_report")
	private EventReportEntity eventReport;
	private String imageUrl;
	private String size;
	private Long width;
	private Long height;
}

