package tp.appliSpring.rest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.mapper.MyMapper;
import tp.appliSpring.model.Compte;
import tp.appliSpring.service.ServiceCompte;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;



//@ExtendWith(SpringExtension.class) //par defaut @WebMvcTest de Spring 7
@WebMvcTest(CompteRestCtrl.class)
public class TestCompteRestCtrl {

    @Autowired
    private MockMvc mvc;

    @MockitoBean //anciennement @MockBean
    private ServiceCompte compteService; //not real implementation but mock to configure

    @MockitoBean //anciennement @MockBean
    private MyMapper myMapper;

    //@Autowired dont work on MyMapper because default @WebMvcTest component scan ignore MyMapper
    private MyMapper realMapper=MyMapper.INSTANCE;

    @Test //à lancer sans le profile withSecurity
    public void testComptesDuClient1WithMockOfCompteService(){
        //préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
        List<CompteEntity> comptesEntities = new ArrayList<>();
        comptesEntities.add(new CompteEntity(1L,"compteA",40.0));
        comptesEntities.add(new CompteEntity(2L,"compteB",90.0));
        //System.out.println("comptesEntities="+comptesEntities);
        List<Compte> comptes = realMapper.compteEntityListToCompteList(comptesEntities);
        //System.out.println("comptes="+comptes);

        Mockito.when(compteService.findByClientNumero(1)).thenReturn(comptesEntities);
        Mockito.when(myMapper.compteEntityListToCompteList(Mockito.anyList())).thenReturn(comptes);

        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/comptes?numClient=1")
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
