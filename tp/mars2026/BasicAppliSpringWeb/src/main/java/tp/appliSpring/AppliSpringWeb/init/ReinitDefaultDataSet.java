package tp.appliSpring.AppliSpringWeb.init;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.appliSpring.AppliSpringWeb.dao.DaoCompte;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

@Component
@Profile("reinit")  //à activer via spring.profiles.default=dev2,reinit ou autre
public class ReinitDefaultDataSet {

    private final DaoCompte daoCompte;

    public ReinitDefaultDataSet(DaoCompte daoCompte) {
        this.daoCompte = daoCompte;
        this.daoCompte.save(new Compte(null, "compte_Xy", 50.0));
    }
}
