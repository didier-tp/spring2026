package tp.appliSpring.service;

import tp.appliSpring.exception.NotFoundException;
import tp.appliSpring.model.Compte;
import tp.appliSpring.model.Operation;

import java.util.List;
import java.util.Optional;

public interface ServiceCompte {
    public Compte searchById(Long numero);

    public Optional<Compte> searchOptionalById(Long numero);

    public List<Operation> searchOperationsPourCompte(long numCompte);

    public List<Compte> searchAll();

    public List<Compte> searchBySoldeMini(double soldeMini);

    public List<Compte> searchByClientNumero(long numClient);

    public void removeById(long numCompte) throws NotFoundException;

    public void update(Compte compte) throws NotFoundException;

    public Compte insert(Compte compte); //en retour l'objet avec clef primaire auto incrémentée

    public void transferer(double montant, long numCompteDebit, long numCompteCred);
}
