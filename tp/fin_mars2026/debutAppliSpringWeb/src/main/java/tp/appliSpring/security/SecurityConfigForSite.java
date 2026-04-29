package tp.appliSpring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Profile("withSecurity")
@EnableMethodSecurity()//pour que le test @PreAuthorize("hasRole('ADMIN')") puisse bien fonctionner
public class SecurityConfigForSite {

	//NB: MyUserDetailsService is in tp.appliSpring.bank.security
	@Autowired @Qualifier("local")
	private UserDetailsService userDetailsServiceLocal;

	@Bean
	@Order(2)
	protected SecurityFilterChain siteFilterChain(HttpSecurity http) throws Exception {

		return http.securityMatcher("/site/**")
				.userDetailsService(userDetailsServiceLocal)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/site/app/**").permitAll()
								.requestMatchers("/site/basic/**").permitAll()
								/*.requestMatchers("/site/bank/**").authenticated()*/
								.requestMatchers("/site/bank/**").hasRole("CUSTOMER")
				)
				.csrf( Customizer.withDefaults() )

				//.formLogin( formLogin -> formLogin.permitAll() )
				.formLogin( formLogin -> formLogin.loginPage("/site/app/login")
						.failureUrl("/site/app/login-error")
						.defaultSuccessUrl("/site/app/toWelcome", false)
						.permitAll())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.build();
		//NB: /site/app/login et /site/app/login-error redigirent tous les deux vers templates/login.html
	}


}
