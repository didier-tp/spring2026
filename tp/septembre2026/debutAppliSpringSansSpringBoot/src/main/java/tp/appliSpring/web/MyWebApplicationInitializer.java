package tp.appliSpring.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	MyWebApplicationInitializer() {
		System.out.println("MyWebApplicationInitializer ...");
	}

	protected String[] getServletMappings() {
		return new String[] { "/mvc/*" }; // URL en :8080/.../mvc/...
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { MyWebAppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[0];
	}

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		super.onStartup(context);
		// String activeProfile = "";
		String activeProfile = "initDataSet";
		context.setInitParameter("spring.profiles.active", activeProfile);
	}
}

/*
 * public class MyWebApplicationInitializer implements WebApplicationInitializer
 * {
 * 
 * @Override public void onStartup(ServletContext servletContext) throws
 * ServletException { //java -Dspring.profiles.active=dev,initDataSet possible
 * dans .bat ou .sh //qui démarre tomcat
 * System.setProperty("spring.profiles.active", "initDataSet");
 * AnnotationConfigWebApplicationContext contextSpringWeb = new
 * AnnotationConfigWebApplicationContext();
 * contextSpringWeb.register(MySpringApplication.class);
 * servletContext.addListener(new ContextLoaderListener (contextSpringWeb )); }
 * 
 * }
 */