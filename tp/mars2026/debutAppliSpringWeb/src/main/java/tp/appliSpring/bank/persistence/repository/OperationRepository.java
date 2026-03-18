package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    //A COMPLETER EN TP

    @Query("SELECT op FROM OperationEntity op WHERE op.compte.numero = :numCpt AND op.dateOp BETWEEN :dateMin AND :dateMax")
    List<OperationEntity> findOperationForCompteNumWithDateBetween(long numCpt , Date dateMin , Date dateMax);
}
