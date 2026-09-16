package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.repository.CompteRepository;

import java.util.List;
import java.util.Optional;

@Service //@Component de type Service métier
@RequiredArgsConstructor
public class ServiceCompteImpl implements ServiceCompte{

    //+ injection de dépendance pour déléguer à CompteRepository
    private final CompteRepository compteRepository;

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

    }

    @Override
    public CompteEntity saveOrUpdate(CompteEntity compteEntity) {
        //if(..) règles de gestion, ...
        return compteRepository.save(compteEntity);
    }

    @Override
    public void transferer(double montant, long numCompteDebiter, long numCompteCredider) {

    }

    @Override
    public List<CompteEntity> findAll() {
        return List.of();
    }

    @Override
    public List<CompteEntity> findBySoldeMini(double mini) {
        return List.of();
    }

    @Override
    public List<CompteEntity> findByClientNumero(long numClient) {
        return List.of();
    }

    @Override
    public List<OperationEntity> findOperationsForCompteNumero(long numCompte) {
        return List.of();
    }
}
