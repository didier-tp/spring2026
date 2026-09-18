package tp.appliSpring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("withSecurity")
public class WithSecurityConfig {
	
	@Bean
	protected SecurityFilterChain withSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/rest/**")
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/**").permitAll()
				)
				.cors( Customizer.withDefaults())
				.csrf( csrf -> csrf.disable() )
				.sessionManagement(sM -> sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
				.build() ;
	}
	
}
