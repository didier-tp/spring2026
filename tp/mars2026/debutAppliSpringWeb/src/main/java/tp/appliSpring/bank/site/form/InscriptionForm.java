package tp.appliSpring.bank.site.form;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


class Sport{
	private String nom;
	private Integer nbHeuresParSemaine;
	
	@Override
	public String toString() {
		return "Sport [nom=" + nom + ", nbHeuresParSemaine=" + nbHeuresParSemaine + "]";
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getNbHeuresParSemaine() {
		return nbHeuresParSemaine;
	}
	public void setNbHeuresParSemaine(Integer nbHeuresParSemaine) {
		this.nbHeuresParSemaine = nbHeuresParSemaine;
	}
	
	
}

//classe pour simplement tester la plupart des syntaxes thymeleaf
//au niveau d'un formulaire de saisie (checkbox, radio , select , date , ....)
public class InscriptionForm {
	
	 public enum Situation { CELIBATAIRE , EN_COUPLE };
	
     private String nom;//input text
     private String pays;//select/option
     private LocalDate dateDebut=LocalDate.now();//input de type date
     private Situation situation;//radio button
     private Boolean sportif=Boolean.FALSE; //checkbox
     private Sport sportPrincipal=new Sport(); //may be null
     
	
    public List<String> getListePays() {
    	return Arrays.asList("France" , "Alemagne" , "Italie" , "Espagne");
    }
     
	@Override
	public String toString() {
		return "InscriptionForm [nom=" + nom + ", pays=" + pays + ", dateDebut=" + dateDebut + ", situation="
				+ situation + ", sportif=" + sportif + ", sportPrincipal=" + sportPrincipal + "]";
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Situation getSituation() {
		return situation;
	}
	public void setSituation(Situation situation) {
		this.situation = situation;
	}
	public Boolean getSportif() {
		return sportif;
	}
	public void setSportif(Boolean sportif) {
		this.sportif = sportif;
	}
	public Sport getSportPrincipal() {
		return sportPrincipal;
	}
	public void setSportPrincipal(Sport sportPrincipal) {
		this.sportPrincipal = sportPrincipal;
	}
     
     
     
}
