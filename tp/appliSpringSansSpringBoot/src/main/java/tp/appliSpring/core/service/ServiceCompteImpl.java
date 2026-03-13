package tp.appliSpring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.BankException;
import tp.appliSpring.core.exception.NotFoundException;

@Service //@Component de type Service
//@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	@Autowired
	@Qualifier("jpa")
	private DaoCompte daoCompte;

	@Transactional()
	//@Transactional(propagation = Propagation.REQUIRED)par défaut
	public void transferer(double montant, long numCptDeb, long numCptCred)throws BankException {
		try {
			Compte cptDeb = daoCompte.findById(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			daoCompte.save(cptDeb);
			
			Compte cptCred = daoCompte.findById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			daoCompte.save(cptCred);
		} catch (Exception e) {
			throw new BankException("echec virement",e);
		}
	}

	@Override
	@Transactional
	public Compte rechercherCompte(long numCpt)throws NotFoundException {
		try {
			//Compte cpt  =daoCompte.findById(numCpt);
			//for(Operation op : cpt.getOperations()); //bidouille affreuse pour eviter lazy
			//cpt.getOperations().size();
			
			Compte cpt = daoCompte.findWithOperations(numCpt);
			return cpt;
		} catch (Exception e) {
			throw new NotFoundException("account not found with numCpt="+numCpt,e);
		}
	}

	@Override
	public Compte sauvegarderCompte(Compte cpt) {
		return daoCompte.save(cpt);
	}
	
	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		//return null; //version zero
		return daoCompte.findAll(); //V1 (provisoire)
		//future version V2 (via un nouvel appel sur DAO exploitant @ManyToOne ou bien ...)
	}
	
	@Override
	public List<Compte> rechercherTousComptes() {
		return daoCompte.findAll();
	}

}
