package tp.appliSpring.bank.core.service;

import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.generic.service.GenericCRUDService;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceTask extends GenericCRUDService<Task> {

}
