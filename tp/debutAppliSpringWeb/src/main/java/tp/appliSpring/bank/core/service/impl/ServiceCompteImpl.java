package tp.appliSpring.bank.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.converter.MyBankGenericMapper;
import tp.appliSpring.bank.core.exception.BankException;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.persistence.entity.ClientEntity;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.repository.ClientRepository;
import tp.appliSpring.bank.persistence.repository.CompteRepository;
import tp.appliSpring.generic.service.GenericServiceDirectImpl;

import java.util.List;


@Service //@Component de type Service
//@Transactional
public class ServiceCompteImpl extends GenericServiceDirectImpl<Compte,CompteEntity,Long> implements ServiceCompte {

	private CompteRepository daoCompte;//dao principal
	private MyBankGenericMapper myBankGenericMapper;
	private ClientRepository daoClient;//dao annexe/secondaire

	@Autowired
	public ServiceCompteImpl(CompteRepository daoCompte, ClientRepository daoClient, MyBankGenericMapper myBankGenericMapper){
		super(Compte.class,CompteEntity.class,daoCompte,myBankGenericMapper);
		this.daoCompte=daoCompte;
		this.daoClient=daoClient;
		this.myBankGenericMapper=myBankGenericMapper;
	}

	@Transactional()
	//@Transactional(propagation = Propagation.REQUIRED)par défaut
	public void transfer(double montant, long numCptDeb, long numCptCred)throws BankException {
		try {
			CompteEntity cptDeb = daoCompte.findById(numCptDeb).get();
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			daoCompte.save(cptDeb);

			CompteEntity cptCred = daoCompte.findById(numCptCred).get();
			cptCred.setSolde(cptCred.getSolde() + montant);
			daoCompte.save(cptCred);
		} catch (Exception e) {
			throw new BankException("echec virement",e);
		}
	}


	@Override
	@Transactional()
	public void fixerProprietaireCompte(long numCompte, long numClient) {
		ClientEntity clientEntity = daoClient.findById(numClient).get();
		CompteEntity compteEntity = daoCompte.findById(numCompte).get();
		clientEntity.getComptes().add(compteEntity);
		daoClient.save(clientEntity);
	}


	@Override
	@Transactional()
	public List<Compte> searchWithMinimumBalance(double soldeMini) {
		List<CompteEntity> compteEntityList = daoCompte.findBySoldeGreaterThanEqual(soldeMini);

		return myBankGenericMapper.map(compteEntityList,Compte.class); //V1 via indirect generic mapper
		//return myBankGenericMapper.getMyBankConverter().compteEntityListToCompteList(compteEntityList); //V2 via specific MyBankConverter based on mapStruct
	}

	@Override
	public List<Compte> searchCustomerAccounts(Long numClient) {
        //version 1 avec conversion entity -> model:
		//return myBankGenericMapper.map(daoCompte.findByClientsNumero(numClient),Compte.class);

        //version 2 avec projection direct en model via @Query Jpa/spring-data:
        return daoCompte.findAsComptesByClientNum(numClient);

	}


}
