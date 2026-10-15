package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
    //http://localhost:8080/appliSpring/rest/api-bank/v1/comptes/1
    @GetMapping("/{numCompte}")
    public Compte getCompteByNum(@PathVariable("numCompte") long numCompte) {
        return serviceCompte.searchById(numCompte);
    }
    */

    /*
    //http://localhost:8080/appliSpring/rest/api-bank/v1/comptes/1
    @GetMapping("/{numCompte}")
    public ResponseEntity<Compte> getCompteByNum(@PathVariable("numCompte") long numCompte) {
        Compte compte = serviceCompte.searchById(numCompte);
        if(compte !=null )
            //return new ResponseEntity<Compte>(compte, HttpStatus.OK);
            return ResponseEntity.ok(compte);
        else
            //return new ResponseEntity<Compte>( HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    */

    //http://localhost:8080/appliSpring/rest/api-bank/v1/comptes/1
    @GetMapping("/{numCompte}")
    public ResponseEntity<Compte> getCompteByNum(@PathVariable("numCompte") long numCompte) {
        return ResponseEntity.of(serviceCompte.searchOptionalById(numCompte));
        //ResponseEntity.of(choseOptional) retourne automatiquement ResponseEntity.ok(choseOptional.get())
        //si pas empty et HttpStatus.NOT_FOUND si empty
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

    //url : .../rest/api-bank/v1/comptes/1
    @DeleteMapping("/{numCompte}")
    public ResponseEntity<?> deleteCompteByNum(@PathVariable("numCompte") long numCompte){
        serviceCompte.removeById(numCompte);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        //NO_CONTENT = 204 = OK mais sans message
        //exception handler may return NOT_FOUND (or INTERNAL_SERVER_ERROR)
        //via automatic try/catch
    }


}
