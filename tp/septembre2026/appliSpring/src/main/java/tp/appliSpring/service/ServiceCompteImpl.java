package tp.appliSpring.service;

import org.springframework.stereotype.Service;
import tp.appliSpring.entity.CompteEntity;

@Service //@Component de type Service métier
public class ServiceCompteImpl implements ServiceCompte{

    //+ injection de dépendance pour déléguer à CompteRepository

    @Override
    public CompteEntity searchById(Long numCompte) {
        return null;
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
}
