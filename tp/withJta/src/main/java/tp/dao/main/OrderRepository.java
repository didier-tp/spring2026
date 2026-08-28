package tp.dao.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.entity.main.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
