package tp.appliSpring.bank.web.api.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration; //Spring6/SpringBoot3
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; //Spring6/SpringBoot3
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest; //Spring7/SpringBoot4
import org.springframework.test.web.servlet.MockMvc;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@WebMvcTest(controllers = { CompteRestCtrl.class } /* ,
           excludeAutoConfiguration = {SecurityAutoConfiguration.class} */)
//@ActiveProfiles({"dev"}) //No profile required here (with mocked service)
//NB: @WebMvcTest  without service layer , service must be mocked !!!
public class TestCompteRestCtrlWithServiceMockWithoutSecurity {
    //A COMPLETER EN TP:
    private MockMvc mvc;

    //A COMPLETER EN TP:
    private ServiceCompte compteService; //not real implementation but mock to configure .

    @BeforeEach
    public void reInitMock() {
//vérification que le service injecté est bien un mock
        assertTrue(Mockito.mockingDetails(compteService).isMock());
//reinitialisation du mock(de scope=Singleton par defaut) sur aspects stub et spy
        Mockito.reset(compteService);
    }
    @Test //à lancer sans le profile withSecurity
    public void testComptesDuClient1WithMockOfCompteService(){
//préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
        List<Compte> comptes = new ArrayList<>();
        comptes.add(new Compte(1L,"compteA",40.0));
        comptes.add(new Compte(2L,"compteB",90.0));
        Mockito.when(compteService.searchCustomerAccounts(1L)).thenReturn(comptes);
        try {
            /*
            MvcResult mvcResult =
                      //A COMPLETER EN TP:
                            .andExpect(jsonPath("$", hasSize(2) ))
                            .andExpect(jsonPath("$[0].label", is("compteA") ))
                            .andExpect(jsonPath("$[1].solde", is(90.0) ))
                            .andReturn();
            System.out.println(">>>>>>>>> jsonResult="
                    +mvcResult.getResponse().getContentAsString());

             */
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }}
}
