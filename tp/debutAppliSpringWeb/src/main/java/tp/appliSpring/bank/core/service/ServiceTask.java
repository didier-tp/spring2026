package tp.appliSpring.bank.core.service;

import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.generic.service.GenericService;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceTask extends GenericService<Task,Long> {

}
