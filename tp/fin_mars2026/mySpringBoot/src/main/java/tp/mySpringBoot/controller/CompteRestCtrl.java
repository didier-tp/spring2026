package tp.mySpringBoot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.mySpringBoot.entity.Compte;
import tp.mySpringBoot.service.ServiceCompte;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/rest/api-bank/v1/comptes" )
public class CompteRestCtrl {

    private final ServiceCompte serviceCompte;

    //http:localhost:8080/mySpringBoot/rest/api-bank/v1/comptes/1
    @GetMapping("/{id}")
    Compte getCompteById(@PathVariable("id") Long numero){
        return serviceCompte.searchById(numero);
    }

}
