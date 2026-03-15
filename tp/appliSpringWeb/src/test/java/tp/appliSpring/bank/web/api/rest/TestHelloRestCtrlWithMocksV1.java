package tp.appliSpring.bank.web.api.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; //Spring6/SpringBoot3
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest; //Spring7/SpringBoot4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.SecurityConfig;
import tp.appliSpring.security.SecurityConfigForRest;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
V1 with @WebMvcTest
 */

@WebMvcTest(controllers = { HelloRestCtrl.class } )
//NB: @WebMvcTest load only some compoent usefull for testing @RestController
//and do not load some components like "@Service , @Repository , ...
@Import(SecurityConfigForRest.class)
public class TestHelloRestCtrlWithMocksV1 {
    @Autowired
    private MockMvc mvc;

    /*
    NB: soit  @WithMockUser(username = "admin1" , roles = {"SUPERUSER","ADMIN" })
    soit get(...).with(user("admin1").password("pwd1").roles("USER","ADMIN"))
     */

    @Test //à lancer sans le profile withSecurity
    @WithMockUser(username = "user1" , roles = {"USER" })
    public void testHelloForAdminAsUser(){
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/hello/for_admin")
                                    .contentType(MediaType.APPLICATION_JSON)
                                  //  .with(user("admin1").password("pwd1").roles("USER","ADMIN"))
                              )
                            .andExpect(status().is( oneOf(403,500)))
                            .andReturn();
            System.out.println(">>>>>>>>>  json status for user requesting for_admin="
                    +mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @WithMockUser(username = "admin1" , roles = {"USER","ADMIN" })
    public void testHelloForAdminAsAdmin(){
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/hello/for_admin")
                                            .contentType(MediaType.APPLICATION_JSON)
                                    //  .with(user("admin1").password("pwd1").roles("USER","ADMIN"))
                            )
                            .andExpect(status().isOk())
                            .andReturn();
            System.out.println(">>>>>>>>> jsonResult="
                    +mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
