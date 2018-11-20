package by.ladyka.club.repository;

import by.ladyka.club.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
}
