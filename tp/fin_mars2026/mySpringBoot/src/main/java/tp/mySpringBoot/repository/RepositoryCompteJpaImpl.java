package tp.mySpringBoot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tp.mySpringBoot.entity.Compte;

import java.util.List;

@Repository
@Transactional
public class RepositoryCompteJpaImpl implements RepositoryCompte {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Compte findById(Long num) {
        return entityManager.find(Compte.class,num);
    }

    @Override
    public List<Compte> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Compte c", Compte.class)
                .getResultList();
    }

    @Override
    //save() au sens saveOrUpdate()
    public Compte save(Compte compte) {
        if(compte.getNumero()==null){
            entityManager.persist(compte); //INSERT INTO
        }else{
            entityManager.merge(compte); //UPDATE SQL
        }
        return compte;
    }

    @Override
    public void deleteById(Long num) {
        Compte c = entityManager.find(Compte.class , num);
       entityManager.remove(c);
    }
}
