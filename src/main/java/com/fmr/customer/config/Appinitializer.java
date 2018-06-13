package com.fmr.customer.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Appinitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebSpringConfig.class);
	    ctx.setServletContext(container);
	    ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
	    servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	}

}
