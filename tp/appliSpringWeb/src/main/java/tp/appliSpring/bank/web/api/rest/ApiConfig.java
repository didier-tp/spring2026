package tp.appliSpring.bank.web.api.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ApiConfig implements WebMvcConfigurer {
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        //configurer.usePathSegment(2).setDefaultVersion("1").addSupportedVersions("1","2");
        configurer.usePathSegment(2).setDefaultVersion("1");
        // avec index_segment=1 ou 2 ou autre selon /segment_0/segment_1/segment_2/...
        // exemple: index=1 pour /api/v{version}/hello à transformer en /api/v1/hello
        // exemple: index=2 pour /rest/api-xyz/v{version}/hello à transformer en /rest/api-xyz/v1/hello

        //configurer.useRequestHeader("X-API-Version"); //si num version véhiculé dans entête des requetes HTTP
        //configurer.useQueryParam("version"); // /hello?version=1 ou /hello?version=2
    }
}

//configuration équivalente avec spring-boot dans application.properties:
/*
spring.mvc.apiversion.default=1
#spring.mvc.apiversion.use.header=API-Version
spring.mvc.apiversion.use.path-segment=2
#spring.mvc.apiversion.use.query-parameter=version
 */