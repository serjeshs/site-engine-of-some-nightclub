package by.ladyka.club.repository.menu;

import by.ladyka.club.entity.menu.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {
    List<MenuOrder> findByEvent_Id(Long eventId);
}
