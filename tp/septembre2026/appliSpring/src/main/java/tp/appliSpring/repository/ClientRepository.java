package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
}
