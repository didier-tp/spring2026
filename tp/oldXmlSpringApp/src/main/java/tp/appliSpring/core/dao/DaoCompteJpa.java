package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliSpring.core.entity.Compte;

//@Repository //@Component de type DAO/Repository
//@Qualifier("jpa")
public class DaoCompteJpa implements DaoCompte {
	                    //initialiser entityManager via META-INF/persistence.xml si pas springBoot
	@PersistenceContext //initialiser entityManager via application.properties au sein d'un projet SpringBoot
	private EntityManager entityManager;

	@Override
	public Compte findById(Long numCpt) {
		return entityManager.find(Compte.class, numCpt);
	}

	/*
	@Override
	public Compte save(Compte compte) {
		try {
			entityManager.getTransaction().begin();
			if(compte.getNumero()==null)
				entityManager.persist(compte);//INSERT INTO
			else
				entityManager.merge(compte);//UPDATE
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return compte; //avec numero plus null (auto_incrémenté)
	}
	*/
	
	@Override
	@Transactional 
	public Compte save(Compte compte) {
			if(compte.getNumero()==null)
				entityManager.persist(compte);//INSERT INTO
			else
				entityManager.merge(compte);//UPDATE
		return compte; //avec numero plus null (auto_incrémenté)
	}

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class)
				           .getResultList();
	}

	@Override
	@Transactional 
	public void deleteById(Long numCpt) {
		Compte compte = entityManager.find(Compte.class, numCpt);
		entityManager.remove(compte);
	}

}
