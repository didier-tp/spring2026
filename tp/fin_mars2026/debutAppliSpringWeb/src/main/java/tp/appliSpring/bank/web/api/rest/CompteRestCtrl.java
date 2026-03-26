package tp.appliSpring.bank.web.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.repository.CompteRepository;
import tp.appliSpring.generic.dto.ApiError;
import tp.appliSpring.generic.exception.EntityNotFoundException;

import java.util.List;


@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/v1/comptes" , headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST ,
		RequestMethod.PATCH , RequestMethod.DELETE , RequestMethod.PUT})
public class CompteRestCtrl {

/*
	//Code potentiellement en erreur à ne pas reproduire:
	@Autowired
	private CompteRepository compteRepository;

	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/bad/1
	@GetMapping("/bad/{id}")
	public CompteEntity badVersionWithoutDtoForGetCompteById(@PathVariable("id") Long numeroCompte) {
		return compteRepository.findById( numeroCompte).get();
		//NB: plantage si pas de @JsonIgnore et généralement sans_DTO = très mauvaise pratique
	}
*/


	private ServiceCompte serviceCompte;

	@Autowired
	public CompteRestCtrl(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}

   /*
	//Get By ID
	//V1 avec DTO et V3 (avec automatisme ExceptionHandler)
	//declencher en mode GET avec
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	@GetMapping("/{id}")
	public Compte getCompteById(@PathVariable("id") String numeroCompte) {
		return serviceCompte.searchById(numeroCompte);
		//NB: l'objet retourné sera automatiquement converti au format json
	}
*/



	//V2 avec ResponseEntity<?> mais sans ExceptionHandler

	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	@GetMapping("/{id}")
	public ResponseEntity<?> getCompteById(@PathVariable("id") String numeroCompte) {
        try {
            Compte compte = serviceCompte.searchById(numeroCompte);
			return ResponseEntity.ok(compte);
        } catch (EntityNotFoundException e) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .body(new ApiError(HttpStatus.NOT_FOUND,"compte inexistant"));
        }
	}


	//GET Multiple
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?soldeMini=50
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?numClient=1
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?soldeMini=50&critere2=val2&critere3=val3
	//...
	@GetMapping()
	public List<Compte> getComptesByCriteria(
			@RequestParam(value="soldeMini",required=false) Double soldeMini,
			@RequestParam(value="numClient",required=false) String numClient) {
		if(soldeMini!=null)
			return serviceCompte.searchWithMinimumBalance(soldeMini);
		else if(numClient!=null)
			return serviceCompte.searchCustomerAccounts(numClient);
		else
			return serviceCompte.searchAll();
	}



	//appelé en mode POST
	//avec url = http://localhost:8181/appliSpring/rest/api-bank/v1/comptes
	//avec dans la partie "body" de la requête
	// { "numero" : null , "label" : "comptequiVaBien" , "solde" : 50.0 }
	//...

	//appelé en mode PUT
	//avec url = http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1
	//avec dans la partie "body" de la requête
	// { "numero" : "1" , "label" : "libelleModifie" , "solde" : 120.0  }
	@PutMapping("/{id}")
	public ResponseEntity<Compte> putCompte(@RequestBody Compte compte, @PathVariable("id") String idToUpdate) {
		compte.setNumero(idToUpdate);
		Compte compteMisAJour = serviceCompte.update(compte);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204 : OK sans aucun message dans partie body
		//exception handler may return NOT_FOUND or INTERNAL_SERVER_ERROR
	}

	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	//@DeleteMapping("/{id}")
	//....
}



