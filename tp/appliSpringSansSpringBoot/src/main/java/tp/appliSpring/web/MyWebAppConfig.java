package tp.appliSpring.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import tp.appliSpring.core.config.CommonConfig;
import tp.appliSpring.core.config.DataSourceConfig;
import tp.appliSpring.core.config.DomainAndPersistenceConfig;

/*
 Cette classe MyWebAppConfig
 est utilisée par tp.appliSpring.web.MyWebApplicationInitializer
 et sert à configurer (sans spring boot) le coeur de Spring-web / Spring-webmvc 
 lorsque l'application .war sera deployée dans tomcat ou un équivalent
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "tp.appliSpring.web" }) // to find @Controller , ... @RestController
@Import({ CommonConfig.class, DataSourceConfig.class, DomainAndPersistenceConfig.class })
public class MyWebAppConfig {
// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	public MyWebAppConfig() {
		System.out.println("MyWebAppConfig load ...");
	}
}