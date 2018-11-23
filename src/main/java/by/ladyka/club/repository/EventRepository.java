package by.ladyka.club.repository;

import by.ladyka.club.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
	List<EventEntity> findAllByStartEventBetweenAndVisibleTrue(LocalDateTime after, LocalDateTime before);
	List<EventEntity> findAllByStartEventGreaterThanAndVisibleTrueOrderByStartEventAsc(LocalDateTime time);
	List<EventEntity> findAllByStartEventGreaterThanAndVisibleTrueAndRepublicPayTrueOrderByStartEventAsc(LocalDateTime time);
	List<EventEntity> findByDescriptionContainingOrNameContainingOrCostTextContaining(String description, String name, String costText, Pageable pg);
	List<EventEntity> findByRecommendationAndStartEventGreaterThanAndVisibleTrueOrderByStartEventAsc(Boolean recommendation, LocalDateTime time);
	long countByDescriptionContainingOrNameContainingOrCostTextContainingAndVisibleTrue(String description, String name, String costText);
}
