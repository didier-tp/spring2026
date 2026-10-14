package tp.appliSpring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.exception.NotFoundException;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.model.Compte;
import tp.appliSpring.repository.CompteRepository;

import java.util.List;
import java.util.Optional;

@Service  //@Component de type service
//@Transactional
@RequiredArgsConstructor
public class ServiceCompteImpl implements ServiceCompte{

    private final CompteRepository compteRepository;

    private final MyMapper myMapper;

    @Override
    public Compte searchById(Long numero) {
        CompteEntity compteEntity = compteRepository.findById(numero).orElse(null);
        return myMapper.compteEntityToCompte(compteEntity);
    }

    @Override
    public Optional<Compte> searchOptionalById(Long numero) {
        return Optional.empty();
    }

    @Override
    public List<Compte> searchAll() {
        return List.of();
    }

    @Override
    public List<Compte> searchBySoldeMini(double soldeMini) {
        return List.of();
    }

    @Override
    public List<Compte> searchByClientNumero(long numClient) {
        return List.of();
    }

    @Override
    public void removeById(long numCompte) throws NotFoundException {

    }

    @Override
    public void update(Compte compte) throws NotFoundException {

    }

    @Override
    public Compte insert(Compte compte) {
        return null;
    }

    @Override
    public void transferer(double montant, long numCompteDebit, long numCompteCred) {

    }
}
