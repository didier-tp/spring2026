package tp.appliSpring.bank.site.form;

public class VirementForm {
	
	private Double montant;
	
	private String numCptDeb;
	private String numCptCred;

	
	public VirementForm() {
	}

	public VirementForm(Double montant, String numCptDeb, String numCptCred) {
		super();
		this.montant = montant;
		this.numCptDeb = numCptDeb;
		this.numCptCred = numCptCred;
	}
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getNumCptDeb() {
		return numCptDeb;
	}

	public void setNumCptDeb(String numCptDeb) {
		this.numCptDeb = numCptDeb;
	}

	public String getNumCptCred() {
		return numCptCred;
	}

	public void setNumCptCred(String numCptCred) {
		this.numCptCred = numCptCred;
	}
}
