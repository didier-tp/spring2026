package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.persistence.entity.CompteEntity;

import java.util.List;

/*
 NB: avec l'extension spring-data , une classe d'implémentation de cette interface
     est générée automatiquement et c'est injectable avec @Autowired
 */

public interface CompteRepository extends JpaRepository<CompteEntity,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.findAll()
	.deleteById()
	....
	*/

	//NB: le code de la requête déclenchée sera généré automatiquement
	//par spring-Data via des conventions de nom de méthode (même pas besoin de @NamedQuery)
	//findBy+Clients+Numero où clients vient du fait qu'il existe .clients dans classe Compte
	// et numero vient du fait qu'il existe .numero dans la classe Client
	List<CompteEntity> findByClientsNumero(Long numCli);

    //Exemple de projection directe vers model/DTO Compte via JPQL dans @Query:
    @Query("SELECT new tp.appliSpring.bank.core.model.Compte(cpt.numero,cpt.label,cpt.solde) FROM ClientEntity cli JOIN cli.comptes cpt WHERE cli.numero=?1 ")
    List<Compte> findAsComptesByClientNum(Long numCli);


	//code de la requete dans @NamedQuery("Compte.findWithOperations")
	CompteEntity findWithOperations(long numCompte);
	
	//le code/la requete de cette méthode va être généré automatiquement (sans @NamedQuery)
	//car on a respecter la convention de nom de methode
	//findBy+Solde+GreaterThanEqual avec Compte.solde qui existe
	List<CompteEntity> findBySoldeGreaterThanEqual(double soldeMini);

    List<CompteEntity> findBySoldeBetween(double soldeMini,double soldeMaxi);

    @Query("SELECT c FROM CompteEntity c WHERE c.solde >= ?1 and c.solde <= ?2")
    List<CompteEntity> findBySoldeEntre(double soldeMini,double soldeMaxi);

    //Exemple de  nativeQuery (SQL , pas JPQL)
    @Query(nativeQuery = true ,
            value="SELECT c.numero , c.label , c.solde FROM compte c WHERE c.solde >= ?1 and c.solde <= ?2")
    List<CompteEntity> nativeFindBySoldeEntre(double soldeMini,double soldeMaxi);
}
