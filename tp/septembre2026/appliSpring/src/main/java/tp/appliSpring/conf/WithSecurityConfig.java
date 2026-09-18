package tp.appliSpring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("withSecurity")
@EnableMethodSecurity()  //pour demander à analyser les  @PreAuthorize("hasAuthority ou hasRole)
public class WithSecurityConfig {
	
	@Bean
	@Order(1)
	protected SecurityFilterChain restFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/rest/**")
				.authorizeHttpRequests(
						auth -> auth
								.requestMatchers(HttpMethod.GET,"/rest/api-bank/v1/comptes/**").permitAll()
								.requestMatchers("/rest/api-bank/v1/comptes/**").authenticated()
				)
				.cors( Customizer.withDefaults())
				.csrf( csrf -> csrf.disable() )
				.sessionManagement(sM -> sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
				.build() ;
	}

	@Bean
	@Order(99)
	protected SecurityFilterChain otherFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/**")
				.authorizeHttpRequests(
						auth -> auth
								.requestMatchers("/**").permitAll()
				).build() ;
	}

}
