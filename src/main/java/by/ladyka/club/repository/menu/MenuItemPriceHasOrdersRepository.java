package by.ladyka.club.repository.menu;

import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemPriceHasOrdersRepository extends JpaRepository<MenuItemPricesHasOrders, Long> {
}
