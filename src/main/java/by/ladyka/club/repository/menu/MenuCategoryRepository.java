package by.ladyka.club.repository.menu;

import by.ladyka.club.entity.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
	List<MenuCategory> findAllByParentIsNull();
}
