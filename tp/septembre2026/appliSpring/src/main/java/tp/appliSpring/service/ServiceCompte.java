package tp.appliSpring.service;

import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

//avec des throws RuntimeException implicites
public interface ServiceCompte {
    public CompteEntity searchById(Long numCompte) throws EntityNotFoundException;
    public Optional<CompteEntity> findById(Long numCompte);
    public void removeById(Long numCompte);
    public CompteEntity saveOrUpdate(CompteEntity compteEntity);
    //....
    public void transferer(double montant,long numCompteDebiter,long numCompteCredider); //transaction importante
    public List<CompteEntity> findAll();
    public List<CompteEntity> findBySoldeMini(double mini);
    public List<CompteEntity> findByClientNumero(long numClient);

    public List<OperationEntity> findOperationsForCompteNumero(long numCompte);

    public CompteEntity searchByIdWithOperations(Long numCompte);
}
