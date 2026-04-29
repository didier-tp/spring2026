package tp.appliSpring.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.GenericConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.core.exception.NotFoundException;
@RestController
@RequestMapping(value="/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;

	
	//URL http://localhost:8080/appliSpringSansSpringBoot/mvc/api-bank/compte/1
	//@RequestMapping(value="/{numCompte}" , method=RequestMethod.GET) 
	@GetMapping(value="/{numCompte}")
	/*
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCompte) { 
	      return GenericConverter.map(serviceCompte.rechercherCompteParNumero(numCompte),CompteDto.class) ;
	}*/
	/*
	public ResponseEntity<?> getCompteByNum(@PathVariable("numCompte") Long numCompte) { 
	      Compte compte =serviceCompte.rechercherCompteParNumero(numCompte) ;
	      if(compte == null)
	    	  return new ResponseEntity<Message>( new Message("compte pas trouvé pour numero= " + numCompte),
	    			                              HttpStatus.NOT_FOUND); //404
	      else 
	    	  return new ResponseEntity<CompteDto>( GenericConverter.map(compte,CompteDto.class), HttpStatus.OK);//200
	}*/
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCompte) throws NotFoundException{ 
	      return GenericConverter.map(serviceCompte.rechercherCompte(numCompte),CompteDto.class) ;
	}
	
	//URL http://localhost:8080/applispringSansSpringBoot/mvc/api-bank/compte
	//ou  http://localhost:8080/applispringSansSpringBoot/mvc/api-bank/compte?soldeMini=120
	//A CODER EN TP
	
	//URL http://localhost:8080/applispringSansSpringBoot/mvc/api-bank/compte
	//en mode POST avec { "numero" : null , "label" : "compteXy" , "solde" : 50.0 }
	//A CODER EN TP
	
}
