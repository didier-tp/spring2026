package tp.appliSpring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()  //ca permet d'interpréter @PreAuthorize(...) dans le code java des controleurs
@Profile("withSecurity")
public class WithSecurityConfig {

    @Bean
    protected SecurityFilterChain myFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/",  "/favicon.ico",
                                        "/**/*.png",   "/**/*.gif",    "/**/*.svg",
                                        "/**/*.jpg",   "/**/*.html",   "/**/*.css",    "/**/*.js").permitAll()
                                .requestMatchers("/swagger-ui/**" , "/swagger-ui/*.*").permitAll()
                                .requestMatchers(HttpMethod.GET,"/rest/api-bank/v1/**").permitAll()
                                .requestMatchers("/rest/api-bank/v1/**").authenticated() )
                .cors( Customizer.withDefaults())
                .csrf( csrf -> csrf.disable() )
                .sessionManagement(sM ->
                                      sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

}
