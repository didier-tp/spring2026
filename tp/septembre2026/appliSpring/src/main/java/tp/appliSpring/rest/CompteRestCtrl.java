package tp.appliSpring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.model.Compte;
import tp.appliSpring.service.ServiceCompte;

import java.util.ArrayList;
import java.util.List;

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

    //V2 avec ResponseEntity
    //http://localhost:8080/appliSpring/rest/bank-api/v1/comptes/1 ou 2
    @GetMapping("/{numCompte}" )
    public ResponseEntity<Compte> getCompteByNum(@PathVariable("numCompte") Long numCompte) {
        CompteEntity compteEntity = null;
        try {
            compteEntity = serviceCompte.findById(numCompte).get();
            Compte compteDto = myMapper.compteEntityToCompte(compteEntity);
            return new ResponseEntity<Compte>(compteDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);//404
        }
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
}
