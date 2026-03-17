package tp.appliSpring.AppliSpringWeb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

public interface DaoCompteJpaDirect  {
    Compte findById(Long numero);
    Compte save(Compte compte);
    //...
}
