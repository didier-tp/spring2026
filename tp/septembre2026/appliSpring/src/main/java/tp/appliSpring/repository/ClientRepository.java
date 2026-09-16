package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.entity.ClientEntity;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

    @Query("SELECT cli.comptes FROM ClientEntity cli WHERE cli.numero= :numClient")
    List<CompteEntity> findComptesOfClientNum(long numClient);
}
