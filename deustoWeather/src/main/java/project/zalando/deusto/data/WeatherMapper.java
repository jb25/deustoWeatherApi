package project.zalando.deusto.data;

import java.util.List;

import project.zalando.deusto.vo.WeatherVO;



public interface WeatherMapper {
	
	List<WeatherVO> getWeatherByCity(String city);
	void addWeather(WeatherVO Weather);
	void dropWeather(String city);
}
