package tp.appliSpring.core.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliSpring.core.entity.Operation;

@Repository //@Component de type DAO/Repository
@Qualifier("jpa")
public class DaoOperationJpa implements DaoOperation {
	                    //initialiser entityManager via META-INF/persistence.xml si pas springBoot
	@PersistenceContext //initialiser entityManager via application.properties au sein d'un projet SpringBoot
	private EntityManager entityManager;

	

	@Override
	@Transactional 
	public Operation save(Operation op) {
			if(op.getNumero()==null)
				entityManager.persist(op);//INSERT INTO
			else
				entityManager.merge(op);//UPDATE
		return op; //avec numero plus null (auto_incrémenté)
	}
  
	
}
