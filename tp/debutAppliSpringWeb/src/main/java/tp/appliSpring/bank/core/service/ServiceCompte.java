package tp.appliSpring.bank.core.service;

import java.util.List;

import tp.appliSpring.generic.service.GenericService;
import tp.appliSpring.bank.core.exception.BankException;
import tp.appliSpring.bank.core.model.Compte;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceCompte extends GenericService<Compte,Long> {
	public void transfer(double montant, long numCptDeb, long numCptCred)throws BankException;
	public List<Compte> searchWithMinimumBalance(double soldeMini); //retourne liste vide si rien trouver
	//...
	List<Compte> searchCustomerAccounts(Long numClient);
	public void fixerProprietaireCompte(long numCompte, long numClient);
}
