package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;
import tp.appliSpring.repository.CompteRepository;
import tp.appliSpring.service.ServiceCompte;

@RestController //@Component de type endpoint api rest
@RequestMapping("/rest/api-bank/v1/comptes")
@RequiredArgsConstructor
public class CompteRestCtrl {

    /*
    //A NE PAS FAIRE !!!(remonter directement des @Entity)
    private final CompteRepository compteRepository;

    //http://localhost:8080/appliSpring/rest/api-bank/v1/comptes/1
    @GetMapping("/{numCompte}" )
    public CompteEntity getCompteByNum(@PathVariable("numCompte") long numCompte){
        return compteRepository.findById(numCompte).get();
    }
    */

    private final ServiceCompte serviceCompte;

    //http://localhost:8080/appliSpring/rest/api-bank/v1/comptes/1
    @GetMapping("/{numCompte}" )
    public Compte getCompteByNum(@PathVariable("numCompte") long numCompte){
        return serviceCompte.searchById(numCompte);
    }


}
