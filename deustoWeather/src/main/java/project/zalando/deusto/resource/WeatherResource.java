package project.zalando.deusto.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.zalando.deusto.dao.WeatherDAO;
import project.zalando.deusto.vo.WeatherVO;


@Path("/weather")
@Api(value = "weather")

public class WeatherResource {

	private static final ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");  

	private static final WeatherDAO WeatherDAO = (WeatherDAO) context
			.getBean("weatherDAO");


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findWeatherByCity")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Error when connecting to server."),
			@ApiResponse(code = 404, message = "No Weather found")})
	@ApiOperation(value = "Returns weathers by city.",
	response = WeatherVO.class)
	
	public List<WeatherVO> getWeatherByCity(@HeaderParam("city") String city) {

		return WeatherDAO.findWeatherByCity(city);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Could't insert data")})
	@ApiOperation(value = "Insert Weathers",
	response = Response.class)
	
	public Response insertWeather(@ApiParam(required = true) WeatherVO weather) {
		
		try {
			WeatherDAO.insertWeather(weather);
			
			return Response.status(200).entity("OK").build();
			
		}catch (Exception e){
			return Response.status(403).entity("Bad data supplied").build();
		}
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid CITY supplied"), @ApiResponse(code = 404, message = "Weather not found")})
	@ApiOperation(value = "Delete Weathers", response = Response.class)
	public Response dropWeather(@ApiParam(required = true) String city) {
		try {
			WeatherDAO.deleteWeather(city);
			return Response.status(200).entity("ok").build();
		}catch (Exception e) {
			return Response.status(404).entity("No Weather found").build();
		}

	}
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addBrand")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Could't insert data")})
	@ApiOperation(value = "Insert brand",
	response = Response.class)
	
	public Response insertBrand(@ApiParam(required = true) String brand) {
		
		try {
			WeatherDAO.addBrand(brand);
			
			return Response.status(200).entity("OK").build();
			
		}catch (Exception e){
			return Response.status(403).entity("Bad data supplied").build();
		}
	}*/
}
