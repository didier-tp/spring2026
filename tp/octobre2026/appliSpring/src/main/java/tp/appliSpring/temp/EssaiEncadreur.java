package tp.appliSpring.temp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
//import org.example.explicit.beans.Encadreur;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EssaiEncadreur {

   // private final Encadreur encadreur;

    @PostConstruct
    public void init(){
       // System.out.println(encadreur.encadrer("spring"));
    }

}
