package tp.appliSpring.bank.core.service;

import java.util.List;


import tp.appliSpring.generic.service.GenericCRUDService;
import tp.appliSpring.bank.core.exception.BankException;
import tp.appliSpring.bank.core.model.Compte;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceCompte extends GenericCRUDService<Compte> {
	public void transfer(double montant, String numCptDeb, String numCptCred)throws BankException;
	public List<Compte> searchWithMinimumBalance(double soldeMini); //retourne liste vide si rien trouver
	//...
	List<Compte> searchCustomerAccounts(String numClient);
	public void fixerProprietaireCompte(String numCompte, String numClient);
}
