package tp.appliSpring.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.service.ServiceCompte;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@WebMvcTest(CompteRestCtrl.class)
public class TestCompteRestCtrl {

    @Autowired
    private MockMvc mvc;

    @MockitoBean //anciennement @MockBean
    private ServiceCompte compteService; //not real implementation but mock to configure

    //@MockitoBean //anciennement @MockBean
    @Autowired
    private MyMapper myMapper;

    //@Autowired
    //private MyMapper realMapper;

    @Test //à lancer sans le profile withSecurity
    public void testComptesDuClient1WithMockOfCompteService(){
        //préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
        List<CompteEntity> comptesEntities = new ArrayList<>();
        comptesEntities.add(new CompteEntity(1L,"compteA",40.0));
        comptesEntities.add(new CompteEntity(2L,"compteB",90.0));
        Mockito.when(compteService.findByClientNumero(1)).thenReturn(comptesEntities);

        /*
        Mockito.when(myMapper.compteEntityListToCompteList(comptesEntities)).
                thenReturn(realMapper.compteEntityListToCompteList(comptesEntities));*/
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/api-bank/compte?numClient=1")
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(2) ))
                            .andExpect(jsonPath("$[0].label", is("compteA") ))
                            .andExpect(jsonPath("$[1].solde", is(90.0) ))
                            .andReturn();
            System.out.println(">>>>>>>>> jsonResult=" +mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
