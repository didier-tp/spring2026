package tp.appliSpring.web.form;

public class VirementForm {
	private Double montant;
	private Long numCptDeb;
	private Long numCptCred;

	
	public VirementForm() {
	}

	public VirementForm(Double montant, Long numCptDeb, Long numCptCred) {
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
	public Long getNumCptDeb() {
		return numCptDeb;
	}
	public void setNumCptDeb(Long numCptDeb) {
		this.numCptDeb = numCptDeb;
	}
	public Long getNumCptCred() {
		return numCptCred;
	}
	public void setNumCptCred(Long numCptCred) {
		this.numCptCred = numCptCred;
	}
	
	
}
