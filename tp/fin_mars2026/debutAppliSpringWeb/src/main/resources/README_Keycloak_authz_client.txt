le fichier keycloak.json n'est que rarement utile
Il n'est utilisé ici que pour que l'application spring-boot
puisse se connecter au serveur keycloak
de manière à obtenir une autorisation
pour invoquer des appels vers d'autres API-REST (externes,...)
============
	<dependency>
			<groupId>org.keycloak</groupId>
			<artifactId>keycloak-authz-client</artifactId>
			<version>20.0.3</version> <!-- should match server version -->
		</dependency>
==============
src/main/resources/keycloak.json
---------
{
  "realm": "sandboxrealm",
  "auth-server-url" : "https://www.d-defrance.fr/keycloak",
  "resource" : "myspringclient",
  "credentials": {
    "secret": "crtwAVOIGxmjXRrosDYxEEKlPCMe18rp"
  }
}
================
restriction : ok seulement avec "direct access grant" alias "grant_type=password"
=============
issue à trouver : comment faire autrement car alias "grant_type=password" déconseillé (à priori plus dans oauth 2.1)
==============
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;

 public static String fetchTokenViaKeyCloakAuthzClient(){
        // create a new instance based on the configuration defined in keycloak.json
        AuthzClient authzClient = AuthzClient.create();

        // create an authorization request
        AuthorizationRequest request = new AuthorizationRequest();

        // send the entitlement request to the server in order to
        // obtain an RPT with all permissions granted to the user
        AuthorizationResponse response = authzClient.authorization("admin1", "pwd1").authorize(request);
        String rpt = response.getToken();

        System.out.println("You got an RPT: " + rpt);
        return rpt;
    }