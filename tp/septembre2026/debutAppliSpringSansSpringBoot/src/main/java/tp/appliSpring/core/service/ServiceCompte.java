package tp.appliSpring.core.service;

import java.util.List;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.BankException;
import tp.appliSpring.core.exception.NotFoundException;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceCompte {
	public void transferer(double montant,long numCptDeb,long numCptCred)throws BankException;
	public Compte rechercherCompte(long numCpt)throws NotFoundException;
	public Compte sauvegarderCompte(Compte cpt);
	//...
	public List<Compte> rechercherComptesDuClient(long numClient);//codé partiellement en V1
	public List<Compte> rechercherTousComptes();
}
