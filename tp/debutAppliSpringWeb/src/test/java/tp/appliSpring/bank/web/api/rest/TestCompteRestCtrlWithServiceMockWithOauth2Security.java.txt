package tp.appliSpring.bank.web.api.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = { CompteRestCtrl.class } )
@ActiveProfiles({"withSecurity"})
//NB: @WebMvcTest  without service layer , service must be mocked !!!
public class TestCompteRestCtrlWithServiceMockWithOauth2Security {
    @Autowired
    private MockMvc mvc;

    @MockitoBean //@MockBean
    private ServiceCompte compteService; //not real implementation but mock to configure .

    @BeforeEach
    public void reInitMock() {
        //vérification que le service injecté est bien un mock
        assertTrue(Mockito.mockingDetails(compteService).isMock());
        //reinitialisation du mock(de scope=Singleton par defaut) sur aspects stub et spy
        Mockito.reset(compteService);
    }

    @Test()
    public void testViaOauth2Code(){
        String token =MyOAuthHelper.fetchTokenGrantTypePassword();
        //String token = MyOAuthHelper.fetchToken();
        System.out.println("oauth token=" + token);
        assertNotNull(token);
        //NB: if lack of permission for this client :
        //400 / Bad Request / Response from server: {"error":"invalid_request","error_description":"Client does not support permissions"}
        //in keycloak admin console , on xxx realm
        //add "client authentification with client secret"
        //and activate "Authorization" to "on"
    }


    @Test //à lancer sans le profile withSecurity
    public void testComptesDuClient1WithMockOfCompteService(){
//préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
        List<Compte> comptes = new ArrayList<>();
        comptes.add(new Compte(1L,"compteA",40.0));
        comptes.add(new Compte(2L,"compteB",90.0));
        Mockito.when(compteService.searchCustomerAccounts(1L)).thenReturn(comptes);
        try {

            //String token = MyOAuthHelper.fetchTokenGrantTypePassword();
            String token = MyOAuthHelper.fetchToken();
            System.out.println("oauth2 token=" + token);

            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/comptes?numClient=1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization" , "Bearer " + token)
                              )
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(2) ))
                            .andExpect(jsonPath("$[0].label", is("compteA") ))
                            .andExpect(jsonPath("$[1].solde", is(90.0) ))
                            .andReturn();
            System.out.println(">>>>>>>>> jsonResult="
                    +mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }}
}
