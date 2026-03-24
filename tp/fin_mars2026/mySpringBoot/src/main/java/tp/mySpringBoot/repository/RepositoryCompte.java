package tp.mySpringBoot.repository;

import tp.mySpringBoot.entity.Compte;

import java.util.List;
//interface de composant spring d'accès aux données
//avec des méthodes CRUD : Create , Retreive , Update , Delete

public interface RepositoryCompte {
    Compte findById(Long num);
    List<Compte> findAll();
    Compte save(Compte compte); //Create(insert into) or Update
    void deleteById(Long num);
    //...
}
