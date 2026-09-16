package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.entity.ClientEntity;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

    @Query("SELECT cli.comptes FROM ClientEntity cli WHERE cli.numero= :numClient")
    List<CompteEntity> findComptesOfClientNum(long numClient);


    //Exemple de projection vers le modèle (ou DTO) "Compte"
    @Query("SELECT new tp.appliSpring.model.Compte(cpt.numero,cpt.label,cpt.solde)   FROM ClientEntity cli JOIN cli.comptes cpt WHERE cli.numero=?1 ")
    List<Compte> findAsComptesByClientNum(Long numCli);
}
