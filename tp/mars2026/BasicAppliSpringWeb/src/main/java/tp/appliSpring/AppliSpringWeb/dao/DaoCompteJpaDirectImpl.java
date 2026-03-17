package tp.appliSpring.AppliSpringWeb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

@Repository //@Component de type "access aux donnees"
public class DaoCompteJpaDirectImpl implements DaoCompteJpaDirect {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Compte findById(Long numero) {
        return entityManager.find(Compte.class, numero);
    }

    @Override
    @Transactional
    public Compte save(Compte compte) {
       if(compte.getNumero()==null){
           entityManager.persist(compte);//INSERT INTO et auto_incr
       }else{
           entityManager.merge(compte);//UPTADE SQL
       }
       return compte;
    }
}
