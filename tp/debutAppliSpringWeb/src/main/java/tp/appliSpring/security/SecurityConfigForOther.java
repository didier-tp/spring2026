package tp.appliSpring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("withSecurity")
public class SecurityConfigForOther {

	@Bean
	@Order(3)
	protected SecurityFilterChain otherFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/**")
		     .authorizeHttpRequests(
				// pour image , html , css , js
				auth -> auth.requestMatchers("/**").permitAll()
						                                 .requestMatchers("/h2-console/**").permitAll()
			 ).cors( Customizer.withDefaults() )
				.csrf( csrf -> csrf.disable() )
				.headers(headers -> headers.frameOptions(
                        frameOptions -> frameOptions.sameOrigin()
                ))//pour h2-console
		     .build();
	}


}
