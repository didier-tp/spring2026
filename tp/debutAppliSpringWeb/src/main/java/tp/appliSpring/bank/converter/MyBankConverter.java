package tp.appliSpring.bank.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.persistence.entity.ClientEntity;
import tp.appliSpring.bank.persistence.entity.CompteEntity;

import java.util.List;

//@Mapper // MyMapper.INSTANCE...
@Mapper(componentModel = "spring") //for @Autowired or ...
public interface MyBankConverter {

    MyBankConverter INSTANCE = Mappers.getMapper( MyBankConverter.class );

    //@Mapping(target="firstname", source="prenom")
    //@Mapping(target="lastname", source="nom")
    Client clientEntityToClient(ClientEntity source);

    ClientEntity clientToClientEntity(Client source);

    Compte compteEntityToCompte(CompteEntity source);
    List<Compte> compteEntityListToCompteList(List<CompteEntity> source);

    CompteEntity compteToCompteEntity(Compte source);
    List<CompteEntity> compteListToCompteEntityList(List<Compte> source);

}

