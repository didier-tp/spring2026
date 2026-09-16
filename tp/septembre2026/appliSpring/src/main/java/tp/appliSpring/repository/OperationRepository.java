package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {
}
