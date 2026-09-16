package tp.appliSpring.reinit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.repository.CompteRepository;

import java.util.List;

@Component
@Profile("reInit")
public class ReinitDefaultDataSet {

    private final CompteRepository compteRepository;

    public ReinitDefaultDataSet(CompteRepository compteRepository) {
        this.compteRepository=compteRepository;
        this.compteRepository.save(new CompteEntity(null,"compteAaa",50.0));
        this.compteRepository.save(new CompteEntity(null,"compteBbb",60.0));
        //pour verifier:
        List<CompteEntity> comptes = this.compteRepository.findAll();
        System.out.println("ReinitDefaultDataSet ,comptes" + comptes);
    }
}
