package tp.appliSpring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()  //ca permet d'interpréter @PreAuthorize(...) dans le code java des controleurs
@Profile("!withSecurity")
public class WithoutSecurityConfig {

    @Bean
    protected SecurityFilterChain myFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(
                        //exemple très permissif ici à grandement adapter !!!!
                        auth -> auth.requestMatchers("/**/*.*").permitAll()
                                                                  .requestMatchers("/**").permitAll())
                .cors( Customizer.withDefaults() )
                .headers(headers ->
                        headers.frameOptions( frameOptions-> frameOptions.sameOrigin()) )
                .csrf(csrf->csrf.disable());
        return http.build();
    }

}
