package tp.appliSpring.bank.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.core.service.ServiceClient;


@Profile("withSecurity")
@Qualifier("local") //used by "/site/..." (SpringMvc & thymeleaf or jsp)
                    //do not used by /rest/... with oauth2/oidc/keycloak
                    //used by "/rest/..." and  withoutOAuth2 profile
@Service()
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ServiceClient serviceCustomer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		logger.debug("MyUserDetailsService.loadUserByUsername() called with username=" + username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String password = null;
		if (username.equals("admin1")) {
			password = passwordEncoder.encode("pwd1");// simulation password ici
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			userDetails = new User(username, password, authorities);
		} else if (username.equals("user1")) {
			password = passwordEncoder.encode("pwd1");// simulation password ici
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			userDetails = new User(username, password, authorities);
		}
		else {
			//NB le username considéré comme potentiellement égal à "client_" + numClient
			try {
				Long numClient = Long.parseLong(username.substring(7));
				Client customer = serviceCustomer.searchById(numClient);
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
                //password = passwordEncoder.encode("pwd"); //en dur (pas recherché en base dans cette version)
				password=customer.getPassword(); //stocké en version déjà crypté en base !!!!
				System.out.println("tp.appliSpring.bank.site.security.MyUserDetailsService: crypted password="+password);
				userDetails = new User(username, password, authorities);	
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		if (userDetails == null) {
			//NB: il est important de remonter UsernameNotFoundException (mais pas null , ni une autre exception)
			//si l'on souhaite qu'en cas d'échec avec cet AuthenticationManager
			//un éventuel AuthenticationManager parent soit utilisé en plan B
			throw new UsernameNotFoundException(username + " not found");
		}
		return userDetails;
		//NB: en retournant userDetails = new User(username, password, authorities);
		//on retourne comme information une association entre usernameRecherché et
		//(bonMotDePasseCrypté + liste des rôles)
		//Le bonMotDePasseCrypté servira simplement à effectuer une comparaison avec le mot
		//de passe qui sera saisi ultérieurement par l'utilisateur
		//(via l'aide de passwordEncoder.matches())
	}
}
