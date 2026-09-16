package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.repository.CompteRepository;
import tp.appliSpring.repository.OperationRepository;

import java.util.List;
import java.util.Optional;

@Service //@Component de type Service métier
//@Transactional
@RequiredArgsConstructor
public class ServiceCompteImpl implements ServiceCompte{

    //+ injection de dépendance pour déléguer à CompteRepository
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    @Override
    public CompteEntity searchById(Long numCompte) {
        return compteRepository.findById(numCompte).get(); //ou bien .orElse(null)
    }

    @Override
    public Optional<CompteEntity> findById(Long numCompte) {
        return compteRepository.findById(numCompte);
    }

    @Override
    public void removeById(Long numCompte) {
         compteRepository.deleteById(numCompte);
    }

    @Override
    public CompteEntity saveOrUpdate(CompteEntity compteEntity) {
        //if(..) règles de gestion, ...
        return compteRepository.save(compteEntity);
    }

    @Override
    public List<CompteEntity> findAll() {
        return compteRepository.findAll();
    }

    @Override
    public List<CompteEntity> findBySoldeMini(double mini) {
        return compteRepository.findBySoldeMini(mini);
    }

    @Override
    public List<CompteEntity> findByClientNumero(long numClient) {
        return compteRepository.findByClients_Numero(numClient);
    }

    @Override
    public List<OperationEntity> findOperationsForCompteNumero(long numCompte) {
        return operationRepository.findByCompteNumero(numCompte);
    }

    @Override
    @Transactional
    public CompteEntity searchByIdWithOperations(Long numCompte) {
        CompteEntity cpt = this.compteRepository.findById(numCompte).get();
        for(OperationEntity op : cpt.getOperations()){
            //boucle for pour remonter en mémoire les élements de la collection en mode lazy
        }
        return cpt;
    }

    @Override
    @Transactional
    public void transferer(double montant, long numCptDeb, long numCptCred) {
        try {
            // transaction globale initialisée dès le début de l'exécution de transferer si @Transactional

            CompteEntity cptDeb = this.compteRepository.findById(numCptDeb).get();
            //le dao exécute son code dans la grande transaction
            //commencée par le service sans la fermer et l'objet cptDeb remonte à l'état persistant
            cptDeb.setSolde(cptDeb.getSolde() - montant);
            this.compteRepository.save(cptDeb); //facultatif si @Transactional

            //idem pour compte à créditer
            CompteEntity cptCred= this.compteRepository.findById(numCptCred).get();
            cptCred.setSolde(cptCred.getSolde() + montant);
            this.compteRepository.save(cptCred) ; //facultatif si @Transactional

            //en fin de transaction réussie (sans exception) , toutes les modification effectuées
            //sur les objets à l'état persistant seront répercutées en base (.save() automatiques)
        } catch (Exception e) {
            throw new RuntimeException("echec virement",e);
            //ou bien throw BankException héritant de RuntimeException
        }
    }
}
