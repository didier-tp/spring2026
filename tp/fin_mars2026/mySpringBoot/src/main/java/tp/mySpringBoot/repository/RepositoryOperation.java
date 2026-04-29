package tp.mySpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.mySpringBoot.entity.Operation;

import java.util.List;

public interface RepositoryOperation extends JpaRepository<Operation,Long> {
    //pas besoin de @Query ici (car convention de nommage findBy + Compte + Numero)
    List<Operation> findByCompteNumero(Long numeroCompte);  //findByAccountNumber si avec noms anglais
}
