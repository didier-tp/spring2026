package tp.dao.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.entity.purchase.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
