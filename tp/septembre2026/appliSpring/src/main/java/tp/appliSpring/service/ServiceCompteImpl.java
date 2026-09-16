package tp.appliSpring.service;

import org.springframework.stereotype.Service;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;

import java.util.List;
import java.util.Optional;

@Service //@Component de type Service métier
public class ServiceCompteImpl implements ServiceCompte{

    //+ injection de dépendance pour déléguer à CompteRepository

    @Override
    public CompteEntity searchById(Long numCompte) {
        return null;
    }

    @Override
    public Optional<CompteEntity> findById(Long numCompte) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long numCompte) {

    }

    @Override
    public CompteEntity saveOrUpdate(CompteEntity compteEntity) {
        return null;
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
