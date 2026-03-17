package tp.appliSpring.AppliSpringWeb.service;

import tp.appliSpring.AppliSpringWeb.entity.Compte;

public interface ServiceCompte {
    Compte searchById(Long numCpt);
    Compte create(Compte compte);
    //...
}
