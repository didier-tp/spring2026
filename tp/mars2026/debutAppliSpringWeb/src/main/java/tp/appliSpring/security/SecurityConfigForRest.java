package tp.appliSpring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tp.appliSpring.security.generic.standalone.JwtAuthenticationFilter;

@Configuration
@Profile("withSecurity")
@EnableMethodSecurity(jsr250Enabled = true)//pour que le test @PreAuthorize("hasRole('ADMIN')") puisse bien fonctionner
public class SecurityConfigForRest {

	@Bean
	@Order(1)
	@Profile("!withoutOAuth2")
	public SecurityFilterChain restFilterChainWithOAuth2(HttpSecurity http) throws Exception {
		return restFilterChainBuilder(http)
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
				//with spring.security.oauth2.resourceserver.jwt.issuer-uri=https://www.d-defrance.fr/keycloak/realms/sandboxrealm
				.build();
	}


	private HttpSecurity restFilterChainBuilder(HttpSecurity http) throws Exception {

		return http.securityMatcher("/rest/**")
		         .authorizeHttpRequests( auth ->
						    auth.requestMatchers(HttpMethod.GET,"/rest/api-bank/v1/comptes/**").permitAll()
								.requestMatchers("/rest/api-bank/v1/**").authenticated()
				              .requestMatchers("/rest/api-auth/v1/standalone-jwt-auth").permitAll())
                  .cors( Customizer.withDefaults())
				.csrf( csrf -> csrf.disable() )
				.sessionManagement(sM -> sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	}




	//NB: MyUserDetailsService is in tp.appliSpring.bank.security
	@Autowired(required = false) @Qualifier("local")
	private UserDetailsService userDetailsServiceLocal;


	@Bean
	@Order(1)
	@Profile("withoutOAuth2")
	public SecurityFilterChain restFilterChainWithoutOAuth2(HttpSecurity http) throws Exception {
		return restFilterChainBuilder(http)
				.userDetailsService(userDetailsServiceLocal)
				.exceptionHandling(eh -> eh.authenticationEntryPoint(getRestAuthenticationEntryPoint())
				    /*eH -> eH.defaultAuthenticationEntryPointFor(
						getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/rest/**")
				    )*/)
				.addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Autowired(required = false) //exists or not (selon profiles)
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired(required = false)
	private PasswordEncoder passwordEncoder;

	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
	}

	@Bean
	@Profile("withoutOAuth2")
	public AuthenticationProvider authenticationProviderwithoutOAuth2(){
		/*
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceLocal); //spring 6
		*/
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsServiceLocal);//spring 7
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	@Profile("withoutOAuth2")
	public AuthenticationManager authenticationManagerWithoutOAuth2(AuthenticationConfiguration authConfig)
		throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
