package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.entity.OperationEntity;

import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    //méthode respecte la convention findBy+"SourPartie_Compte" + "SousSousParie_Numero"
    //en anglais : findByAccountNumber()
    List<OperationEntity> findByCompteNumero(long numCompte);
}
