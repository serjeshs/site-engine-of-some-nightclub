package by.ladyka.club.repository.menu;

import by.ladyka.club.entity.MenuOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuOrderRepository extends JpaRepository<MenuOrderEntity, Long> {
}
