package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring.entity.CompteEntity;

public interface CompteRepository extends JpaRepository<CompteEntity,Long> {
    //principales méthodes héritées : .findAll() , .findById() , .save() , .deleteById()
}
