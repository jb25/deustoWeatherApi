package project.zalando.deusto.impl;

import java.util.List;

import project.zalando.deusto.dao.WeatherDAO;
import project.zalando.deusto.data.WeatherMapper;
import project.zalando.deusto.vo.WeatherVO;

public class WeatherDAOImpl implements WeatherDAO{

	private WeatherMapper WeatherMapper;

	public void setWeatherMapper(WeatherMapper WeatherMapper) {
		this.WeatherMapper = WeatherMapper;
	}


	public List<WeatherVO> findWeatherByCity(String city) {

		return WeatherMapper.getWeatherByCity(city);
	}


	public void insertWeather(WeatherVO Weather) {
		WeatherMapper.addWeather(Weather);

	}


	public void deleteWeather(String city) {
		WeatherMapper.dropWeather(city);

	}

}
