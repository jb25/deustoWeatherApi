package project.zalando.deusto.test;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import project.zalando.deusto.dao.WeatherDAO;
import project.zalando.deusto.util.ApiRequest;
import project.zalando.deusto.vo.WeatherVO;




@WebService
public class Test {

	@WebMethod
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		WeatherDAO weatherDAO = (WeatherDAO) context.getBean("weatherDAO");

		// Inserción de un usuario			
		
		List<WeatherVO> weather = new ArrayList<>();
		weather = ApiRequest.getWeatherByCity("bilbao");

		WeatherVO weatherVO = new WeatherVO();


		for (int i = 0; i < weather.size(); i++) {
			if(weather.get(i).getDate() != weatherVO.getDate() && weatherVO != null ){
				weatherVO.setCity(weather.get(i).getCity());
				weatherVO.setDate(weather.get(i).getDate());
				weatherVO.setDayDesc(weather.get(i).getDayDesc());
				weatherVO.setNightDesc(weather.get(i).getNightDesc());
				weatherVO.setTemperatureMax(weather.get(i).getTemperatureMax());
				weatherVO.setTemperatureMin(weather.get(i).getTemperatureMin());
				weatherDAO.insertWeather(weatherVO);
			}
		}




	}	
}
