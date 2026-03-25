package tp.mySpringBoot.service;

import tp.mySpringBoot.entity.Compte;

public interface ServiceCompte {
    Compte searchById(Long numCompte);
    //...
}
