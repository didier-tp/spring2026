package tp.appliSpring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;

@Mapper(componentModel = "spring") //for @Autowired
public interface MyMapper {

    //MyMapper INSTANCE = Mappers.getMapper( MyMapper.class ); //pour acces sans spring

    Compte compteEntityToCompte(CompteEntity source);
}
