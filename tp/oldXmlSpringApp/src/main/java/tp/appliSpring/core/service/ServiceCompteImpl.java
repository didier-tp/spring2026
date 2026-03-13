package tp.appliSpring.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.SoldeInsuffisantException;

//@Service //classe de Service prise en charge par spring
public class ServiceCompteImpl implements ServiceCompte{
	
	
	//@Autowired  + //@Qualifier("jdbc ou jpa ou simu")
	private DaoCompte daoCompte=null;
	
	//setter for xml injection config:
	public void setDaoCompte(DaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

	public ServiceCompteImpl() {
		super();
		System.out.println("dans constructeur de ServiceCompteImpl , daoCompte="+daoCompte);
		//impossible de déleguer des appels à daoCompte qui est null
	}
	
	@PostConstruct()
	public void initialiser() {
		System.out.println("dans méthode préfixée par @PostConstruct() , daoCompte="+daoCompte);
		//possible ici de déleguer des appels à daoCompte qui n'est plus null
	}

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		return daoCompte.findById(numero);
	}
	
	@Override
	public Compte sauvegarderCompte(Compte compte) {
		return daoCompte.save(compte);
	}

	@Override
	public List<Compte> rechercherTousComptes() {
		return daoCompte.findAll();
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void supprimerCompte(long numCpt) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(/*propagation = Propagation.REQUIRED*/) //REQUIRED par defaut
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		try {
			// transaction globale initialisée dès le début de l'exécution de effectuerVirement
			Compte cptDeb = this.daoCompte.findById(numCptDeb); //le dao exécute son code dans la grande transaction
			//commencée par le service sans la fermer et l'objet cptDeb remonte à l'état persistant
			
			if(cptDeb.getSolde() < montant)
				throw new SoldeInsuffisantException("compte a débiter qui a un solde insuffisant : " + cptDeb);
			
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			//this.daoCompte.save(cptDeb); //appel de .save() possible et dans ce cas base modifiée temporairement seulement
			                               //avec rollback ultérieur possible en cas d'exception
			
			
			//idem pour compte à créditer
			Compte cptCred= this.daoCompte.findById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			//this.daoCompte.save(cptCred)

			
			//en fin de transaction réussie (sans exception) , toutes les modification effectuées sur les objets
			//à l'état persistant seront répercutées en base (.save() automatiques)
		} catch (Exception e) {
			throw new RuntimeException("echec virement " + e.getMessage() , e); //rollback se fait de façon fiable
			//ou bien throw new ClasseExceptionPersonnaliseeHeritanttDeRuntimeException("echec virement" , e);
		}
	}
}
