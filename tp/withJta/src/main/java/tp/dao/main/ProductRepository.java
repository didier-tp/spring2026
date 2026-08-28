package tp.dao.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.entity.main.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
