package tp.appliSpring.reinit;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.repository.CompteRepository;

@Component
@Profile("reinit")
@RequiredArgsConstructor
public class ReinitDataSet {

    private final CompteRepository compteRepository;

    @PostConstruct
    public void reiniDefaultDataSet(){
        this.compteRepository.save(new CompteEntity(null,"compteC1",100.0));
        this.compteRepository.save(new CompteEntity(null,"compteC2",20.0));
        this.compteRepository.save(new CompteEntity(null,"compteC3",150.0));
        this.compteRepository.save(new CompteEntity(null,"compteC4",60.0));
    }
}
