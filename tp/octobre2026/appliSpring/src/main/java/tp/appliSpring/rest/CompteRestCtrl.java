package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;
import tp.appliSpring.repository.CompteRepository;
import tp.appliSpring.service.ServiceCompte;

import java.util.List;

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
    @GetMapping("/{numCompte}")
    public Compte getCompteByNum(@PathVariable("numCompte") long numCompte) {
        return serviceCompte.searchById(numCompte);
    }

    //RECHERCHE MULTIPLE :
     //URL de déclenchement: ...../rest/api-bank/v1/comptes
    //                    ou ...../rest/api-bank/v1/comptes?soldeMini=80
    @GetMapping()
    public List<Compte> getComptesByCriteria(
            @RequestParam(value = "soldeMini", required = false) Double soldeMini) {
        if (soldeMini == null)
            return serviceCompte.searchAll();
        else
            return serviceCompte.searchBySoldeMini(soldeMini);
    }


}
