package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliSpring.core.entity.Compte;

//@Repository //@Component de type DAO/Repository
//@Qualifier("jpa")
public class DaoCompteJpa implements DaoCompte {
	                    //initialiser entityManager via META-INF/persistence.xml si pas springBoot
	//@PersistenceContext //initialiser entityManager via application.properties au sein d'un projet SpringBoot
	private EntityManager entityManager;

	@Override
	public Compte findById(Long numCpt) {
		// A CODER/COMPLETER EN TP
		return null;
	}
	
	@Override
	public Compte findWithOperations(Long numCpt) {
		return entityManager
				.createNamedQuery("Compte.findWithOperations", Compte.class)
				.setParameter(1,numCpt).getSingleResult();
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
		  // A CODER/COMPLETER EN TP
		   //	Compte compte = …………………..
			//	entityManager……….(compte);
	}

}
