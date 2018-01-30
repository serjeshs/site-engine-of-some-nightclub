package by.ladyka.club.repository;

import by.ladyka.club.entity.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
