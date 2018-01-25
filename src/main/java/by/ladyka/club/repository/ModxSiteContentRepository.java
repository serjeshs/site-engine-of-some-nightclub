package by.ladyka.club.repository;

import by.ladyka.club.entity.old.ModxSiteContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModxSiteContentRepository extends JpaRepository<ModxSiteContent, Long> {
}
