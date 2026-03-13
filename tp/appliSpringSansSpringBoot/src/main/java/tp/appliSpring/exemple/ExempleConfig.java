package tp.appliSpring.exemple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.exemple" , "tp.appliSpring.aspect"})
@EnableAspectJAutoProxy //pour prendre en compte les @Aspect
public class ExempleConfig {
/* @ComponentScan() pour demander à spring de parcourir les classes de certains
packages pour y trouver des annotations @Component , @Service , @Autowired à
analyser et interpréter */
}
