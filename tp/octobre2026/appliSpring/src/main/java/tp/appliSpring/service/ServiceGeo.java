package tp.appliSpring.service;

import java.util.List;

public interface ServiceGeo {
    String getVilleByCodePostal(String codePostal);
    List<String> getDepartementsByRegion(String region);
}
