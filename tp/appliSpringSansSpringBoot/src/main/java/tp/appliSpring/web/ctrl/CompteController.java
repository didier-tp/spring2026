package tp.appliSpring.web.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.web.form.VirementForm;

@Controller // cas particulier de @Component (pour crontroller web de spring mvc)
@SessionAttributes(value = { "numClient" }) // ou bien client (de classe Client) en V2
//noms des "modelAttributes" qui sont EN PLUS récupérés/stockés
//en SESSION HTTP au niveau de la page de rendu
//--> visibles en requestScope ET en sessionScope
@RequestMapping("/compte")
public class CompteController {
	@Autowired
	private ServiceCompte serviceCompte;

	

	@RequestMapping("/initLogin")
	public String initLogin(Model model) {
		return "login"; // pour demander la vue login.jsp
	}

	@ModelAttribute("numClient")
	public Long addClientInModel() {
		return 0L; // valeur par defaut
	}
	
	@ModelAttribute("virementForm")
	public VirementForm addDefaultVirementInModel() {
		return new VirementForm(); // valeur par defaut
	}

	@RequestMapping("/verifLogin")
	public String verifLogin(Model model, @RequestParam(name = "numClient", required = false) Long numClient) {
		if (numClient == null) {
			model.addAttribute("message", "numClient doit être une valeur numerique");
			return "login"; // si rien de saisi , on réinvite à mieux saisir (login.jsp)
		}
		model.addAttribute("numClient", numClient); // ou objet client en v2
		return comptesDuClient(model); // même fin de traitement que route "/compteDuClient" .
	}

	@RequestMapping("/comptesDuClient")
	public String comptesDuClient(Model model) {
		Long numClient = (Long) model.getAttribute("numClient"); // ou objet "Client" en V2
		List<Compte> comptesPourClient = serviceCompte.rechercherComptesDuClient(numClient);
		model.addAttribute("listeComptes", comptesPourClient);
		return "comptes"; // pour demander la vue comptes.jsp
	}
	
	@RequestMapping("/virement")
	public String versVirement(Model model) {
		comptesDuClient(model);//store numClient et listeComptes dans model
		return "virement"; // pour demander la vue virement.jsp
	}

	
	 @RequestMapping("/effectuerVirement")
	 public String effectuerVirement(Model model,@ModelAttribute("virementForm") VirementForm virementForm) 
	 {
	String message =""; 
	 try { 
		serviceCompte.transferer(virementForm.getMontant(), virementForm.getNumCptDeb(), virementForm.getNumCptCred()); 
		 message = "virement bien effectué" ;
	   }
	 catch (Exception e) { 
		 message = "echec virement :" + e.getMessage();
		} 
	  model.addAttribute("message",message);
	  return comptesDuClient(model);
	 }
	 
	 
}