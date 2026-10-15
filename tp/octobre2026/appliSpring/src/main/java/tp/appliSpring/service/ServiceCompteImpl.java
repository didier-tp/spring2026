package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.exception.NotFoundException;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.model.Compte;
import tp.appliSpring.model.Operation;
import tp.appliSpring.repository.CompteRepository;

import java.util.List;
import java.util.Optional;

@Service  //@Component de type service
//@Transactional
@RequiredArgsConstructor
public class ServiceCompteImpl implements ServiceCompte{

    private final CompteRepository compteRepository;

    private final MyMapper myMapper;

    @Override
    public Compte searchById(Long numero) {
        CompteEntity compteEntity = compteRepository.findById(numero).orElse(null);
        return myMapper.compteEntityToCompte(compteEntity);
    }

    @Override
    public Optional<Compte> searchOptionalById(Long numero) {
        return Optional.empty();
    }


    @Override
    public List<Operation> searchOperationsPourCompte(long numCompte) {
        CompteEntity compteEntity = compteRepository.rechercherCompteAvecOperations(numCompte);
        return myMapper.operationEntityListToOperationList(compteEntity.getOperations());
    }

    /*
    @Override
    @Transactional
    public List<Operation> searchOperationsPourCompte(long numCompte) {
        CompteEntity compteEntity = compteRepository.findById(numCompte).get();
        return myMapper.operationEntityListToOperationList(compteEntity.getOperations());
    }
   */

    @Override
    public List<Compte> searchAll() {
        return myMapper.compteEntityListToCompteList (compteRepository.findAll());
    }

    @Override
    public List<Compte> searchBySoldeMini(double soldeMini) {
        //Au moins deux impléméntations possibles :
        //  * avec conversion mapStruct
        //  * avec projection direct JPAQL de CompteEntity vers model.compte
        return myMapper.compteEntityListToCompteList (
                compteRepository.findBySoldeGreaterThanEqual(soldeMini));
    }

    @Override
    public List<Compte> searchByClientNumero(long numClient) {
        return List.of();
    }

    @Override
    public void removeById(long numCompte) throws NotFoundException {

    }

    @Override
    public void update(Compte compte) throws NotFoundException {

    }

    @Override
    public Compte insert(Compte compte) {
        return null;
    }

    @Override
    @Transactional(/*propagation = Propagation.REQUIRED*/)
    public void transferer(double montant, long numCompteDebit, long numCompteCred) {

        try {
            CompteEntity cptDeb = this.compteRepository.findById(numCompteDebit).get();
            cptDeb.setSolde(cptDeb.getSolde()-montant);
            this.compteRepository.save(cptDeb);

            CompteEntity cptCred = this.compteRepository.findById(numCompteCred).get();
            cptCred.setSolde(cptCred.getSolde()+montant);
            this.compteRepository.save(cptCred);
        } catch (Exception e) {
            throw new RuntimeException("echec virement",e);//déclenchera un rollback si @Transactional
        }

    }
}
