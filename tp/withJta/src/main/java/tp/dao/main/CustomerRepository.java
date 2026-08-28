package tp.dao.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.entity.main.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
