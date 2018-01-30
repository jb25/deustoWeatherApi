package project.zalando.deusto.dao;

import java.util.List;

import project.zalando.deusto.vo.WeatherVO;

public interface WeatherDAO {

	List<WeatherVO> findWeatherByCity(String city);
	void insertWeather(WeatherVO Weather);
	void deleteWeather(String city);
	
	
}
