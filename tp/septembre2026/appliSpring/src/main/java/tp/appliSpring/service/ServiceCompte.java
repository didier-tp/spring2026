package tp.appliSpring.service;

import tp.appliSpring.entity.CompteEntity;

public interface ServiceCompte {
    public CompteEntity searchById(Long numCompte);
    public void removeById(Long numCompte);
    public CompteEntity saveOrUpdate(CompteEntity compteEntity);
    //....
    public void transferer(double montant,long numCompteDebiter,long numCompteCredider); //transaction importante
}
