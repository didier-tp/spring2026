package tp.appliSpring.bank.site.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceClient;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.site.form.VirementForm;
import tp.appliSpring.generic.exception.EntityNotFoundException;

import java.util.List;


@Controller
@RequestMapping("/site/bank")
@SessionAttributes({"client" , "numClient" , "password" })
public class BankController {
	@Autowired
	private ServiceClient serviceClient;

	@Autowired
	//@Qualifier("direct") //by default @Primary
	//@Qualifier("hex")
	private ServiceCompte serviceCompte;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute("client")
	public Client addDefaultClientAttributeInModel() {
		//NB: new ClientDto(Long numero, String prenom, String nom, String email, String adresse)
		return new Client(null,"prenom?", "nom?" ,"ici_ou_la@xyz.com" , "adresse ?");
	}

	@ModelAttribute("compte")
	public Compte addDefaultCompteAttributeInModel() {
		//NB: new CompteDto( numero,  label, solde)
		return new Compte(null,"", 0.0);
	}

	@ModelAttribute("virement")
	public VirementForm addDefaultVirementAttributeInModel() {
		//NB: new VirementForm(montant, numCptDeb, numCptCred)
		return new VirementForm(null,null, null);
	}

	private Long automaticNumClientRetreiveAfterSpringSecurityLogin(){
		Long numCli = null;
        //on récupère le username de l'utilisateur loggé avec spring security
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                String username =auth.getName();
                System.out.println("client WithSecurity , username="+username);
                //on considère que username vaut (par convention dans ce Tp) "client_" + numClient
                //et on extrait donc le numero du client authentifié:
                Long numClient= Long.parseLong(username.substring(7));
                System.out.println("client WithSecurity , numClient="+numClient);
                numCli=numClient;
            }
        } catch (Exception e) {
			System.err.println("automaticNumClientRetreiveAfterSpringSecurityLogin: " + e.getMessage());
        }
        return numCli;
	}

	@RequestMapping("/espace_client")
	 public String clientLogin(Model model,
			 @RequestParam(name="numClient", required = false)  Long numClient) {
		System.out.println("/site/bank/espace_client with numClient="+numClient );
		String message="";
		if(numClient==null )
			numClient = automaticNumClientRetreiveAfterSpringSecurityLogin();
		if(numClient==null )
			message="numClient is required";
		if(numClient!=null) {
            try {
                Client client = serviceClient.searchById(numClient);
                model.addAttribute("client", client);
                String cryptedPwd = client.getPassword();
                System.out.println("/site/bank/espace_client: cryptedPwd="+cryptedPwd);
            } catch (EntityNotFoundException e) {
                message=e.getMessage();
            }
        }
	    
		model.addAttribute("message", message);
		model.addAttribute("numClient", numClient);
	    return "espace_client_bank"; //aiguiller sur la vue "espace_client_bank"
	 }

	@RequestMapping("comptesDuClient")
	public String comptesDuClient(Model model) {
		/* Long numClient=(Long)model.getAttribute("numClient");

		 */
		Client client = (Client) model.getAttribute("client");
		Long numClient = client!=null ? client.getNumero() : null;

		if(numClient==null)
			return "clientLogin";
		/*else*/
		List<Compte> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
		//System.out.println("listeComptes="+listeComptes);
		model.addAttribute("listeComptes", listeComptes);
		return "comptes";
	}

	@RequestMapping("toAddCompte")
	public String toAddCompte(Model model) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null)
			return "client_login";
		return "add_compte";
	}

	//NB: un @ModelAttribute("xxx") défini dans un controller 1
	//semble être accessible depuis un controller 2 .


	@RequestMapping("doAddCompte")
	public String doAddCompte(Model model,
							  @Valid @ModelAttribute("compte") Compte compte,
							  BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// form validation error
			System.out.println("form validation error: " + bindingResult.toString());
			return "add_compte";
		}
		/*else*/
		try {
			Long numClient=(Long)model.getAttribute("numClient");
			if(numClient==null)
				return "clientLogin";
			compte = serviceCompte.create(compte);
			serviceCompte.fixerProprietaireCompte(compte.getNumero(), numClient);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			return "add_compte";
		}
		return comptesDuClient(model); //réactualiser et afficher nouvelle liste des comptes
	}

	@RequestMapping("toVirement")
	public String toVirement(Model model,HttpSession httpSession) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null)
			return "clientLogin";
		List<Compte> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
		model.addAttribute("listeComptes", listeComptes);  //pour listes déroulantes (choix numCptDeb et numCptCred)
		return "virement";
	}


	@RequestMapping("doVirement")
	public String doVirement(Model model,
							 HttpSession httpSession,
							 @Valid @ModelAttribute("virement") VirementForm virement,
							 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// form validation error
			System.out.println("form validation error: " + bindingResult.toString());
			return "virement";
		}
		try {
			serviceCompte.transfer(virement.getMontant(), virement.getNumCptDeb(), virement.getNumCptCred());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			Long numClient=(Long)model.getAttribute("numClient");
			List<Compte> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
			model.addAttribute("listeComptes", listeComptes);
			return "virement";
		}
		return comptesDuClient(model); //réactualiser et afficher nouvelle liste des comptes
	}

}
