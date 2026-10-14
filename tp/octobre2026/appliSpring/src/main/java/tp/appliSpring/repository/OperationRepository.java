package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.entity.OperationEntity;

import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    //le sql sera automatiquement généré depuis l'analyse du nom de la méthode
    // "findBy" + "Compte" + "Numero"  (sous et sous-sous partie de OperationEntity)
    //                   si tout serait en anglais : findByAccountNumber()
    public List<OperationEntity> findByCompteNumero(Long numCompte);
}
