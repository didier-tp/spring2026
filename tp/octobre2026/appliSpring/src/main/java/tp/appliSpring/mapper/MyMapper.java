package tp.appliSpring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.model.Compte;
import tp.appliSpring.model.Operation;

import java.util.List;

@Mapper(componentModel = "spring") //for @Autowired
public interface MyMapper {

    //MyMapper INSTANCE = Mappers.getMapper( MyMapper.class ); //pour acces sans spring

    Compte compteEntityToCompte(CompteEntity source);
    List<Compte> compteEntityListToCompteList(List<CompteEntity> source);
    CompteEntity compteToCompteEntity(Compte source);

    Operation operationEntityToOperation(OperationEntity source);
}
