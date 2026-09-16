package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.service.ServiceCompte;

@RestController  //component de type pointEntree ApiRest
@RequestMapping(value="/rest/bank-api/v1/comptes")
@RequiredArgsConstructor
public class CompteRestCtrl {

    private final ServiceCompte serviceCompte;

    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes/1 ou 2
    @GetMapping("/{numCompte}" )
    public CompteEntity getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        return serviceCompte.findById(numCompte).get();
    }
}
