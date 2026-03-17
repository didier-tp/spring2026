package tp.appliSpring.AppliSpringWeb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.AppliSpringWeb.dao.DaoCompte;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

@Service //cas particulier de @Component
@Transactional
@RequiredArgsConstructor //pour gérer le constructeur d'injection de dépendance
public class ServiceCompteImpl implements ServiceCompte{

    private final DaoCompte daoCompte;

    @Override
    public Compte searchById(Long numCpt) {
        return daoCompte.findById(numCpt).get();
    }

    @Override
    public Compte create(Compte compte) {
        return daoCompte.save(compte);
    }
}
