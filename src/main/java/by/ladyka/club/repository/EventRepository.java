package by.ladyka.club.repository;

import by.ladyka.club.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findAllByStartEventBetween(LocalDateTime after, LocalDateTime before);
	List<Event> findAllByStartEventGreaterThanOrderByStartEventAsc(LocalDateTime time);
	List<Event> findByDescriptionContainingOrNameContainingOrCostTextContaining(String description, String name, String costText, Pageable pg);
	long countByDescriptionContainingOrNameContainingOrCostTextContaining(String description, String name, String costText);
}
