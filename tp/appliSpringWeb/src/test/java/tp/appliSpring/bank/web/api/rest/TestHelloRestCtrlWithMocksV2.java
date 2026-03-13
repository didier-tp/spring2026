package tp.appliSpring.bank.web.api.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tp.appliSpring.AppliSpringApplication;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
V2 with @SpringBootTest (without @WebMvcTest but with @BeforeEach)
 */

@SpringBootTest(classes={AppliSpringApplication.class})
@ActiveProfiles({"withSecurity"})
public class TestHelloRestCtrlWithMocksV2 {

    //to initialize in @BeforeEach
    private MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /*
    NB: soit  @WithMockUser(username = "admin1" , roles = {"SUPERUSER","ADMIN" })
    soit get(...).with(user("admin1").password("pwd1").roles("USER","ADMIN"))
     */

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

    @Test
    @WithMockUser(username = "user1" , roles = {"USER" })
    public void testHelloForAdminAsUser(){
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/hello/for_admin")
                                            .contentType(MediaType.APPLICATION_JSON)
                            )
                            .andExpect(  status().is( oneOf(403,500)))
                            .andReturn();
            System.out.println(">>>>>>>>>  json status for user requesting for_admin="
                    +mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @WithAnonymousUser
    public void testHelloForAuthenticatedUserAsAnonymous(){
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/hello/for_authenticated_user")
                                            .contentType(MediaType.APPLICATION_JSON)
                                    //  .with(user("admin1").password("pwd1").roles("USER","ADMIN"))
                            )
                            .andExpect(status().is(401))
                            .andReturn();
            System.out.println(">>>>>>>>> json status for anonymous="
                    +mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @WithMockUser(username = "user1" , roles = {"USER" })
    public void testHelloForAuthenticatedUserAsUser(){
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-bank/v1/hello/for_authenticated_user")
                                    .contentType(MediaType.APPLICATION_JSON)
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

