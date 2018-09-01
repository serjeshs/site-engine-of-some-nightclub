package by.ladyka.club.repository;

import by.ladyka.club.entity.BePaidRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BePaidRequestsRepository extends JpaRepository<BePaidRequest, Long> {
}
