package tp.appliSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!withSecurity")
public class WithoutSecurityConfig {
	
	@Bean
	protected SecurityFilterChain withoutSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/**")
		    .authorizeHttpRequests(
				auth -> auth.requestMatchers("/**").permitAll()
						                                 .requestMatchers("/h2-console/**").permitAll()
				)
		  .cors( Customizer.withDefaults() )
		  .headers(headers -> headers.frameOptions(
                    frameOptions -> frameOptions.sameOrigin()
          ))//pour h2-console
		  .csrf( csrf -> csrf.disable() )
		  .build();
	}

}
