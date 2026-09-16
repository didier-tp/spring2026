package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.repository.CompteRepository;
import tp.appliSpring.repository.OperationRepository;

import java.util.List;
import java.util.Optional;

@Service //@Component de type Service métier
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
        return List.of();
    }

    @Override
    public List<OperationEntity> findOperationsForCompteNumero(long numCompte) {
        return operationRepository.findByCompteNumero(numCompte);
    }

    @Override
    public void transferer(double montant, long numCompteDebiter, long numCompteCredider) {

    }
}
