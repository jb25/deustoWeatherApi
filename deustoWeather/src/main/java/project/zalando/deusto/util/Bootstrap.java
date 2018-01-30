package project.zalando.deusto.util;

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class Bootstrap extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("Weathers usuarios");
		beanConfig.setDescription("Users API for weather project");
		beanConfig.setTermsOfServiceUrl("http://deustoweather.org/");
		beanConfig.setVersion("1.0.0");
		beanConfig.setContact("support@weather.org");
		beanConfig.setSchemes(new String[]{"http"});   
		beanConfig.setHost("localhost:8080/zalando.deusto-0.0.1-SNAPSHOT");
		beanConfig.setBasePath("api");
		beanConfig.setResourcePackage("project.zalando.deusto.resource");
		beanConfig.setScan(true);
	}
}