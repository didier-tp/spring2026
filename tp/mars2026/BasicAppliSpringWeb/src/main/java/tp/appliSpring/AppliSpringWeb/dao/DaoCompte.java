package tp.appliSpring.AppliSpringWeb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long> {
    //par héritage , on récupère certaines méthodes prédéfinies:
    //.findById() , .findAll() , .save() , .deleteById()
}
