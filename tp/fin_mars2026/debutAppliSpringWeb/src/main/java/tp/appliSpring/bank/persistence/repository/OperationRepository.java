package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    //A COMPLETER EN TP
}
