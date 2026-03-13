package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.bank.persistence.entity.TaskEntity;

/*
 NB: avec l'extension spring-data , une classe d'implémentation de cette interface
     est générée automatiquement et c'est injectable avec @Autowired
 */

public interface TaskRepository extends JpaRepository<TaskEntity,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.findAll()
	.deleteById()
	....
	*/
}
