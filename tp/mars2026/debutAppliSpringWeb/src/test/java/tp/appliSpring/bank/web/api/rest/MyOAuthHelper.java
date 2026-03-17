package tp.appliSpring.bank.web.api.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

public class MyOAuthHelper {

    //OK mais à ne pas beaucoup utiliser car grant_type=password déconseillé
    //NB: dans console admin de keycloak on peut désactiver le "grant_type=password"
    //en décochant "direct access grant" au sein de Authentication flow du client
    //et dans ce cas 400 Bad Request: "{"error":"unauthorized_client","error_description":"Client not allowed for direct access grants"}"
    public static String fetchTokenGrantTypePassword(){
        //fetch keycloak token via a MediaType.APPLICATION_FORM_URLENCODED request:

        RestClient restClient = RestClient.create();
        String uriBase = "https://www.d-defrance.fr/keycloak/realms/sandboxrealm";
        //String clientId = "anywebappclient";
        String clientId = "myspringclient";
        //String clientSecret = null;
        String clientSecret = "crtwAVOIGxmjXRrosDYxEEKlPCMe18rp";


        MultiValueMap<String, String> formParamMap= new LinkedMultiValueMap<>();
        formParamMap.add("username", "admin1");
        formParamMap.add("password", "pwd1");
        formParamMap.add("client_id", clientId);
        if(clientSecret!=null)
            formParamMap.add("client_secret", clientSecret);
        formParamMap.add("grant_type", "password"); //OK mais à améliorer car grant_type=password déconseillé .

        ResponseEntity<Map> responseFetchToken = restClient.post()
                .uri(uriBase + "/protocol/openid-connect/token")
                .body(formParamMap)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .toEntity(Map.class);

        System.out.println("responseFetchToken.status="+responseFetchToken.getStatusCode().value());
        System.out.println("responseFetchToken.body as map="+responseFetchToken.getBody());
        return (String) responseFetchToken.getBody().get("access_token");
    }


    //fetchToken() with code grant_type and pkce is difficult to implement directly
    public static String fetchToken(){
        return "????";
    }



}
