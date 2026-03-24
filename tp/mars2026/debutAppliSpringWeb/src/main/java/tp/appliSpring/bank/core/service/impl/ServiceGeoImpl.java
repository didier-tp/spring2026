package tp.appliSpring.bank.core.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.core.service.ServiceGeo;

import java.util.List;

@Service
//@Transactional
public class ServiceGeoImpl implements ServiceGeo {

    @CacheEvict(value="villesByCodePostal",allEntries = true)
    @Scheduled(fixedRateString = "${spring.caching/myTTL:2000}") //2s par defaut
    public void resetCache(){
        //epmty code , indirect effect with @CacheEvict
        System.out.println("reset cache");
    }

    @Override
    @Cacheable("villesByCodePostal")
    public String getVilleByCodePostal(String codePostal) {
        System.out.println("ServiceGeoImpl.getVilleByCodePostal() with codePostal = " + codePostal);
        switch (codePostal) {
            case "75000":
                return "Paris";
            case "69000":
                return "Lyon";
            case "13000":
                return "Marseille";
        }
        return "?"; //code postal non géré
    }

    @Override
    @Cacheable("departementsByRegion")
    public List<String> getDepartementsByRegion(String region) {
        System.out.println("ServiceGeoImpl.getDepartementsByRegion() with region = " + region);
        switch(region) {
            case "Ile-de-France":
                return List.of("Paris", "Seine-et-Marne", "Yvelines", "Essonne", "Hauts-de-Seine", "Seine-Saint-Denis", "Val-de-Marne", "Val-d'Oise");
            case "Auvergne-Rhône-Alpes":
                return List.of("Ain", "Allier", "Ardèche", "Cantal", "Drôme", "Isère", "Loire", "Haute-Loire", "Puy-de-Dôme", "Rhône", "Savoie", "Haute-Savoie");
            case "Provence-Alpes-Côte d'Azur":
                return List.of("Alpes-de-Haute-Provence", "Hautes-Alpes", "Alpes-Maritimes", "Bouches-du-Rhône", "Var", "Vaucluse");
        }
        return List.of();// region non gérée
    }
}
