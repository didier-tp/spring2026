package tp.appliSpring.mapper;

import org.mapstruct.Mapper;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyMapper {

    public Compte compteEntityToCompte(CompteEntity source);
    public List<Compte> compteEntityListToCompteList(List<CompteEntity> source);

    public CompteEntity compteToCompteEntity(Compte source);
}
