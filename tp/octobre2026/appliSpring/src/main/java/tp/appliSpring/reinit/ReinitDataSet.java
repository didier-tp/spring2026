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

@Component
@Profile("reinit")
@RequiredArgsConstructor
public class ReinitDataSet {

    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    @PostConstruct
    public void reiniDefaultDataSet(){
        CompteEntity compteC1 =this.compteRepository.save(new CompteEntity(null,"compteC1",100.0));
        this.compteRepository.save(new CompteEntity(null,"compteC2",20.0));
        this.compteRepository.save(new CompteEntity(null,"compteC3",150.0));
        this.compteRepository.save(new CompteEntity(null,"compteC4",60.0));

        operationRepository.save(new OperationEntity(null,"opC1a",-5.5, LocalDate.now(),compteC1));
        operationRepository.save(new OperationEntity(null,"opC1b",-6.6, LocalDate.now(),compteC1));
    }
}
