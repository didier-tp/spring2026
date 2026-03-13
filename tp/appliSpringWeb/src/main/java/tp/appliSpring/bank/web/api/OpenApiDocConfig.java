package tp.appliSpring.bank.web.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@OpenAPIDefinition
public class OpenApiDocConfig {
    //private static final String JWT_BEARER_SCHEME = "JwtBearerAuth";
    private static final String OAUTH_SCHEME = "OAuth";

    //@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri:'?'}")
    //String authURL;

    @Value("${springdoc.oAuthFlow.authorizationUrl:'?'}")
    String authorizationUrl;

    @Value("${springdoc.oAuthFlow.tokenUrl:'?'}")
    String tokenUrl;

    @Bean
    @Profile("!withSecurity")
    public OpenAPI withoutSecurityOpenAPI(Components components) {
        return this.baseOpenAPI(components);
    }

    @Bean
    @Profile("withSecurity")
    public OpenAPI withSecurityOpenAPI(Components components) {
        var openapiConfig = this.baseOpenAPI(components);
        /*
        openapiConfig.addSecurityItem(new SecurityRequirement().addList(JWT_BEARER_SCHEME)); //+ with .addSecuritySchemes(JWT_BEARER_SCHEME, bearerTokenSecurityScheme) in .components()
         */
        openapiConfig.addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME)); //+ with .addSecuritySchemes(OAUTH_SCHEME, oAuthScheme) in .components()
        return openapiConfig;
    }

    public OpenAPI baseOpenAPI(Components components) {
        var openapiConfig = new OpenAPI()
                //.servers(servers)
                .info(new Info()
                        .title("api-bank")
                        .description("minibank api (tp)")
                        .version("v1")
                        .license(new License().name("GNU/GPL").url("https://www.gnu.org/licenses/gpl-3.0.html"))
                )
                .components(components);
        return openapiConfig;
    }

    @Bean
    @Profile("!withSecurity")
    public Components withoutSecurityOpenAPIComponents() {
        return this.baseOpenAPIComponents();
    }

    @Bean
    @Profile("withSecurity")
    public Components withSecurityOpenAPIComponents() {
        /*
        var bearerTokenSecurityScheme = new SecurityScheme()
                .name(JWT_BEARER_SCHEME)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT").scheme("bearer");
        */
        var oauthFlow = new OAuthFlow()
                .authorizationUrl(this.authorizationUrl)
                .tokenUrl(this.tokenUrl)
                .scopes(new Scopes());
        var oAuthScheme = new SecurityScheme()
                .name(OAUTH_SCHEME)
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows().authorizationCode(oauthFlow));

        Components components = this.baseOpenAPIComponents();
        //components.addSecuritySchemes(JWT_BEARER_SCHEME, bearerTokenSecurityScheme);
        components.addSecuritySchemes(OAUTH_SCHEME, oAuthScheme);
        return components;
    }

    public Components baseOpenAPIComponents() {
        var noContentResponse = new ApiResponse().description("Sucessfull Operation With NO_CONTENT");

        var compteSchema = new ObjectSchema()
                .name("Compte")
                .title("Compte")
                .description("Bank Account")
                .addProperties("numero", new IntegerSchema().example("1"))
                .addProperties("label", new StringSchema().example("myBankAccount"))
                .addProperties("solde", new NumberSchema().example("50.0"));
        var compteContent = new Content()
                .addMediaType("application/json" ,
                        new MediaType().schema(compteSchema));

        var compteResponse = new ApiResponse()
                .description("Compte").content(compteContent);
        var createdCompteResponse = new ApiResponse()
                .description("Created Compte (with id)").content(compteContent);

        var apiErrorSchema = new ObjectSchema()
                .name("ApiError")
                .title("ApiError")
                .description("ApiError message")
                .addProperties("status", new StringSchema().example("NOT_FOUND or INTERNAL_SERVER_ERROR or ..."))
                .addProperties("message", new StringSchema().example("xyz not found or internal server error or ..."))
                .addProperties("timestamp", new StringSchema().example("2024-11-26 08:45:55"));

        var apiErrorContent = new Content()
                .addMediaType("application/json" ,
                        new MediaType().schema(apiErrorSchema));
        var internalServerErrorResponse = new ApiResponse()
                .description("Internal Server Error").content(apiErrorContent);
        var notFoundErrorResponse = new ApiResponse()
                .description("Not Found").content(apiErrorContent);

        return new Components()
                .addSchemas("CompteSchema" , compteSchema)
                .addSchemas("ApiErrorSchema" , apiErrorSchema)
                .addResponses("NoContentResponse",noContentResponse)
                .addResponses("InternalServerErrorResponse",internalServerErrorResponse)
                .addResponses("NotFoundErrorResponse",notFoundErrorResponse)
                .addResponses("CompteResponse",compteResponse)
                .addResponses("CreatedCompteResponse",createdCompteResponse);
    }

}
