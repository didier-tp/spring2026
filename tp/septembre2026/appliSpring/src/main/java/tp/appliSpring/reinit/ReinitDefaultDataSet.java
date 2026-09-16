package tp.appliSpring.reinit;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;
import tp.appliSpring.repository.CompteRepository;
import tp.appliSpring.repository.OperationRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("reInit")
@RequiredArgsConstructor
public class ReinitDefaultDataSet {

    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    @PostConstruct
    public void reinitDataSet() {
        CompteEntity compteA= this.compteRepository.save(new CompteEntity(null,"compteAaa",50.0));
        CompteEntity compteB= this.compteRepository.save(new CompteEntity(null,"compteBbb",60.0));

        operationRepository.save(new OperationEntity(null,"achat1",-6.6 , LocalDate.now(),compteA));
        operationRepository.save(new OperationEntity(null,"achat2",-7.6 , LocalDate.now(),compteA));
        //pour verifier:
        List<CompteEntity> comptes = this.compteRepository.findAll();
        System.out.println("ReinitDefaultDataSet ,comptes" + comptes);
    }
}
