package tp.mySpringBoot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tp.mySpringBoot.entity.Compte;

import java.util.List;
import java.util.Optional;

/*
Implementation  directe via JPA/entityManager
qui n'est plus nécessaire en nouvelle version SpringData
 */


//@Repository
@Transactional
public class RepositoryCompteJpaImpl /* implements RepositoryCompte */ {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<Compte> findById(Long num) {
        return Optional.of(entityManager.find(Compte.class,num));
    }


    public List<Compte> findByLabelLike(String label) {
        return entityManager.createQuery("SELECT c FROM Compte c WHERE c.label LIKE :label", Compte.class)
                .setParameter("label",label)
                .setMaxResults(5) //pour limiter la taille de la réponse à 5 eléments au maximum
                .getResultList();
    }


    public List<Compte> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Compte c", Compte.class)
                .getResultList();
    }


    //save() au sens saveOrUpdate()
    public Compte save(Compte compte) {
        if(compte.getNumero()==null){
            entityManager.persist(compte); //INSERT INTO
        }else{
            entityManager.merge(compte); //UPDATE SQL
        }
        return compte;
    }

    public void deleteById(Long num) {
        Compte c = entityManager.find(Compte.class , num);
       entityManager.remove(c);
    }
}
