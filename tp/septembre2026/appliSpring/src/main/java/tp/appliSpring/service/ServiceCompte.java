package tp.appliSpring.service;

import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;

import java.util.List;
import java.util.Optional;

//avec des throws RuntimeException implicites
public interface ServiceCompte {
    public CompteEntity searchById(Long numCompte);
    public Optional<CompteEntity> findById(Long numCompte);
    public void removeById(Long numCompte);
    public CompteEntity saveOrUpdate(CompteEntity compteEntity);
    //....
    public void transferer(double montant,long numCompteDebiter,long numCompteCredider); //transaction importante
    public List<CompteEntity> findAll();
    public List<CompteEntity> findBySoldeMini(double mini);
    public List<CompteEntity> findByClientNumero(long numClient);

    public List<OperationEntity> findOperationsForCompteNumero(long numCompte);
}
