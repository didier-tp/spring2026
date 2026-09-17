package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.model.Compte;
import tp.appliSpring.service.ServiceCompte;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController  //component de type pointEntree ApiRest
@RequestMapping(value="/rest/bank-api/v1/comptes")
@RequiredArgsConstructor
public class CompteRestCtrl {

    private final ServiceCompte serviceCompte;
    private final MyMapper myMapper;

    /*
    //V1
    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes/1 ou 2
    @GetMapping("/{numCompte}" )
    public Compte getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        CompteEntity compteEntity = serviceCompte.findById(numCompte).get();
        //Compte compteDto = new Compte(compteEntity.getNumero(),compteEntity.getLabel(),compteEntity.getSolde());
        Compte compteDto = myMapper.compteEntityToCompte(compteEntity);
        return compteDto;
    }
    */

    /*
    //V2 avec ResponseEntity
    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes/1 ou 2
    @GetMapping("/{numCompte}" )
    public ResponseEntity<Compte> getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        CompteEntity compteEntity = null;
        try {
            compteEntity = serviceCompte.findById(numCompte).get();
            Compte compteDto = myMapper.compteEntityToCompte(compteEntity);
            //return new ResponseEntity<Compte>(compteDto, HttpStatus.OK);
            return ResponseEntity.ok(compteDto);
        } catch (Exception e) {
            //return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);//404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    */

    /*
    //V3 avec ResponseEntity.of
    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes/1 ou 2
    @GetMapping("/{numCompte}" )
    public ResponseEntity<Compte> getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        Optional<CompteEntity> compteEntityOptional = serviceCompte.findById(numCompte);
        Optional<Compte> compteOptional = compteEntityOptional.map((compteEntity)->myMapper.compteEntityToCompte(compteEntity));
        return ResponseEntity.of(compteOptional); //retournant automatiquement ok() ou .notFound()
    }
     */

    //V4 avec ExceptionHandler et appel à .searchById retournant EntityNotFoundException
    @GetMapping("/{numCompte}" )
    public Compte getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        CompteEntity compteEntity = serviceCompte.searchById(numCompte);
        return myMapper.compteEntityToCompte(compteEntity);
    }


    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes
    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes?soldeMini=0.0
    @GetMapping()
    public List<Compte> getComptesByCriteria(@RequestParam(value="soldeMini",required=false) Double soldeMini) {
        List<CompteEntity> compteEntityList = new ArrayList<>();
        if(soldeMini!=null) {
            compteEntityList = serviceCompte.findBySoldeMini(soldeMini);
        }
        else
            compteEntityList=serviceCompte.findAll();
        return myMapper.compteEntityListToCompteList(compteEntityList);
    }

    //appelé en mode POST
    //avec url = http://localhost:8080/appliSpring/rest/bank-api/v1/comptes
    //avec dans la partie "body" de la requête { "id" : null , "label" : "…." , "solde" : 50.0 } si model.Compte
    //ou mieux encore { "label" : "…." , "solde" : 50.0 } avec dto.CompteToCreate héritant de model.Compte
    @PostMapping("")
    public ResponseEntity<?> postCompte(/*@Valid*/ @RequestBody Compte obj) {
        CompteEntity compteEntityToSave = myMapper.compteToCompteEntity(obj);
        CompteEntity savedObjEntity = serviceCompte.saveOrUpdate(compteEntityToSave); //avec id auto_incrémenté
        Compte savedObj = myMapper.compteEntityToCompte(savedObjEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedObj.getNumero()).toUri();
        return ResponseEntity.created(location).body(savedObj);
    }
}
