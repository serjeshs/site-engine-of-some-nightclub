package by.ladyka.club.repository;

import by.ladyka.club.entity.EventReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventReportRepository extends JpaRepository<EventReportEntity, Long> {
	List<EventReportEntity> findAllByVisibleIsTrueOrderByStartEventDesc();
}
