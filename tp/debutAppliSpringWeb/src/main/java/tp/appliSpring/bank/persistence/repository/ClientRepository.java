package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.bank.persistence.entity.ClientEntity;

/*
 NB: avec l'extension spring-data , une classe d'implémentation de cette interface
     est générée automatiquement et c'est injectable avec @Autowired
 */

public interface ClientRepository extends JpaRepository<ClientEntity,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.findAll()
	.deleteById()
	....
	*/

	//méthode de recherche spécifique codée ici via
	//@NameQuery de nom = ClientEntity.findWithAccountById
	ClientEntity findWithAccountById(long numeroCli);
}
