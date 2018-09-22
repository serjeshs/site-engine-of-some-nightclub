package by.ladyka.club.repository;

import by.ladyka.club.entity.UserDataStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataStatisticRepository extends JpaRepository<UserDataStatisticEntity, Long> {
}
