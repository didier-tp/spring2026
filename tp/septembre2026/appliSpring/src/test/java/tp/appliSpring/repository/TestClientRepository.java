package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.entity.ClientEntity;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {AppliSpringApplication.class})
@ActiveProfiles({ "dev"})  //pour analyser application-dev.properties
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //injection par constructeur avec lombok et final
public class TestClientRepository {

    private final ClientRepository clientRepository;//à tester
    private final CompteRepository compteRepository; //pour aider à tester

    @Test
    public void testComptesDuclient(){
        CompteEntity compteX = this.compteRepository.save(new CompteEntity(null,"compteX",100.0));
        CompteEntity compteY =this.compteRepository.save(new CompteEntity(null,"compteY",150.0));
        ClientEntity client1 = new ClientEntity(null,"Bon","jean");
        client1.getComptes().add(compteX); client1.getComptes().add(compteY);
        clientRepository.save(client1);
        //hypothese : base avec tables vides au lancement du test

        CompteEntity compteZ =this.compteRepository.save(new CompteEntity(null,"compteZ",80.0));
        CompteEntity compteW=this.compteRepository.save(new CompteEntity(null,"compteW",60.0));
        ClientEntity client2 = new ClientEntity(null,"Aimare","jean");
        client2.getComptes().add(compteZ); client2.getComptes().add(compteW);
        clientRepository.save(client2); //.save() du coté client là où il n'ya pas mappedBy

        //List<CompteEntity> comptesDuClient1 = clientRepository.findComptesOfClientNum(client1.getNumero());
        List<CompteEntity> comptesDuClient1 = compteRepository.findByClients_Numero(client1.getNumero());
        assertTrue(comptesDuClient1.size()==2);
        log.debug("comptesDuClient1="+comptesDuClient1);

        //tester la version avec projection:
        List<Compte> comptesDtoDuClient2=  clientRepository.findAsComptesByClientNum(client2.getNumero());
        assertTrue(comptesDtoDuClient2.size()==2);
        log.debug("comptesDtoDuClient2="+comptesDtoDuClient2);

    }

    @Test
    @Sql({"/import_comptes.sql"})
    public void testComptesAvecSoldeMini(){

        List<CompteEntity> compteAvecSoldeAuMoins2000 = this.compteRepository.findBySoldeGreaterThanEqual(2000.0);
        //List<CompteEntity> compteAvecSoldeAuMoins2000 = this.compteRepository.findBySoldeMini(2000.0); //ok
        assertTrue(compteAvecSoldeAuMoins2000.size()>=2);
        log.debug("compteAvecSoldeAuMoins2000=" + compteAvecSoldeAuMoins2000);

    }
}
