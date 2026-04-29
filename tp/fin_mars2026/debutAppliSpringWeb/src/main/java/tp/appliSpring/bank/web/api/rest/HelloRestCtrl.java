package tp.appliSpring.bank.web.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.web.api.dto.CompteToCreate;

import java.net.URI;
import java.util.List;


@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/v1/hello" , headers="Accept=application/json")
/*
Basic @RestController for testing of @PreAuthorize("authenticated") , @PreAuthorize("hasRole('ADMIN')")
via MockMvc or ...
 */
public class HelloRestCtrl {

	private String sayHello(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Hello " + authentication.getPrincipal().toString();
	}

	//rest/api-bank/v1/hello/for_authenticated_user
	@PreAuthorize("authenticated")
	@GetMapping("/for_authenticated_user")
	public String getHelloMessageForAuthenticatedUser() {
		return sayHello();
	}

	//rest/api-bank/v1/hello/for_admin
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/for_admin")
	public String getHelloMessageForUserWithAdminRole() {
		return sayHello();
	}

}


