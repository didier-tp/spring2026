package tp.appliSpring.bank.core.service;

import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.generic.service.GenericCRUDService;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceClient extends GenericCRUDService<Client> {

	public Client searchByIdWithAccounts(String numeroCli);
	//...
}
