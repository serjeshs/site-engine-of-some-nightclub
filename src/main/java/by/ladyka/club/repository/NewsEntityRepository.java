package by.ladyka.club.repository;

import by.ladyka.club.entity.NewsEntity;
import by.ladyka.club.entity.old.ModxSiteContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsEntityRepository  extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findAllByVisibleIsTrueOrderByCreatedDateDesc();
}