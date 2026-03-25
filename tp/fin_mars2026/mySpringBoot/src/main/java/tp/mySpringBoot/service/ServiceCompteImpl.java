package tp.mySpringBoot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp.mySpringBoot.entity.Compte;
import tp.mySpringBoot.repository.RepositoryCompte;

@Service
@RequiredArgsConstructor
public class ServiceCompteImpl implements ServiceCompte{

    private final RepositoryCompte repositoryCompte;

    @Override
    public Compte searchById(Long numCompte) {
        return repositoryCompte.findById(numCompte).orElse(null);
    }
}
