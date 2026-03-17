package tp.appliSpring.AppliSpringWeb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliSpring.AppliSpringWeb.entity.Compte;
import tp.appliSpring.AppliSpringWeb.service.ServiceCompte;

@RestController //@Component de type api rest
@RequiredArgsConstructor
@RequestMapping("api-bank/v1/comptes")
public class CompteRestCtrl {

    private final ServiceCompte serviceCompte;

    //http://localhost:8080/springApp/api-bank/v1/comptes/1
    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable("id") Long id){
        return serviceCompte.searchById(id);
    }
}
