package by.ladyka.club.repository;

import by.ladyka.club.entity.EventReportImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventReportImageRepository extends JpaRepository<EventReportImageEntity, Long> {
}
