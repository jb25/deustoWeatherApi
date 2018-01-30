package project.zalando.deusto.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import project.zalando.deusto.vo.WeatherVO;

public class ApiRequest {
	static final String apiUserKey = "zlE21SPfk3EhwnZLJVwY2GAcrSHq22DR";
	static String cityKey = "00001";
	static String cityName = "bilbao";
	static String weather = "-";
	static WeatherVO weatherObjec;
	static List<WeatherVO> weatherObjecs;   //esto es la clave
	/**
	 * @autor jb
	 */
	//	public static void main(String[] args) {
	//
	//		cityKey = "00001";
	//		cityName = "bilbao";
	//		weatherObjecs = getWeatherByCity(cityName);
	//		System.out.println(weatherObjecs.get(1).getTemperatureMax());
	//
	//	}

	public static List<WeatherVO> getWeatherByCity(String cityName) {
		JSONArray weatherData = null;

		//		a partir del nombre de la city, saco la citykey
		String cityWeather = callURL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey="+apiUserKey+"&q="+cityName);
		cityWeather= cityWeather.substring(1);
		cityWeather = cityWeather.substring(0, cityWeather.length() - 1);
		System.out.println("\nOutput cityWeather json response: \n" + cityWeather);

		//		String cityWeather ="{\"Version\":1,\"Key\":\"309382\",\"Type\":\"City\",\"Rank\":32,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"EUR\",\"LocalizedName\":\"Europe\",\"EnglishName\":\"Europe\"},\"Country\":{\"ID\":\"ES\",\"LocalizedName\":\"Spain\",\"EnglishName\":\"Spain\"},\"AdministrativeArea\":{\"ID\":\"PV\",\"LocalizedName\":\"Basque Country\",\"EnglishName\":\"Basque Country\",\"Level\":1,\"LocalizedType\":\"Autonomous Community\",\"EnglishType\":\"Autonomous Community\",\"CountryID\":\"ES\"},\"TimeZone\":{\"Code\":\"CET\",\"Name\":\"Europe/Madrid\",\"GmtOffset\":1.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":\"2018-03-25T01:00:00Z\"},\"GeoPosition\":{\"Latitude\":43.258,\"Longitude\":-2.923,\"Elevation\":{\"Metric\":{\"Value\":10.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":32.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":false,\"SupplementalAdminAreas\":[{\"Level\":3,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\"}],\"DataSets\":[\"Alerts\",\"MinuteCast\"]},{\"Version\":1,\"Key\":\"106790\",\"Type\":\"City\",\"Rank\":85,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"SAM\",\"LocalizedName\":\"South America\",\"EnglishName\":\"South America\"},\"Country\":{\"ID\":\"CO\",\"LocalizedName\":\"Colombia\",\"EnglishName\":\"Colombia\"},\"AdministrativeArea\":{\"ID\":\"TOL\",\"LocalizedName\":\"Tolima\",\"EnglishName\":\"Tolima\",\"Level\":1,\"LocalizedType\":\"Department\",\"EnglishType\":\"Department\",\"CountryID\":\"CO\"},\"TimeZone\":{\"Code\":\"COT\",\"Name\":\"America/Bogota\",\"GmtOffset\":-5.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":null},\"GeoPosition\":{\"Latitude\":3.276,\"Longitude\":-75.749,\"Elevation\":{\"Metric\":{\"Value\":1973.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":6474.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":false,\"SupplementalAdminAreas\":[],\"DataSets\":[]},{\"Version\":1,\"Key\":\"2330252\",\"Type\":\"City\",\"Rank\":85,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"EUR\",\"LocalizedName\":\"Europe\",\"EnglishName\":\"Europe\"},\"Country\":{\"ID\":\"ES\",\"LocalizedName\":\"Spain\",\"EnglishName\":\"Spain\"},\"AdministrativeArea\":{\"ID\":\"MD\",\"LocalizedName\":\"Madrid\",\"EnglishName\":\"Madrid\",\"Level\":1,\"LocalizedType\":\"Autonomous Community\",\"EnglishType\":\"Autonomous Community\",\"CountryID\":\"ES\"},\"TimeZone\":{\"Code\":\"CET\",\"Name\":\"Europe/Madrid\",\"GmtOffset\":1.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":\"2018-03-25T01:00:00Z\"},\"GeoPosition\":{\"Latitude\":40.429,\"Longitude\":-3.702,\"Elevation\":{\"Metric\":{\"Value\":669.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":2194.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":false,\"ParentCity\":{\"Key\":\"308526\",\"LocalizedName\":\"Madrid\",\"EnglishName\":\"Madrid\"},\"SupplementalAdminAreas\":[],\"DataSets\":[\"Alerts\",\"MinuteCast\"]},{\"Version\":1,\"Key\":\"780840\",\"Type\":\"City\",\"Rank\":85,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"ASI\",\"LocalizedName\":\"Asia\",\"EnglishName\":\"Asia\"},\"Country\":{\"ID\":\"PH\",\"LocalizedName\":\"Philippines\",\"EnglishName\":\"Philippines\"},\"AdministrativeArea\":{\"ID\":\"ALB\",\"LocalizedName\":\"Albay\",\"EnglishName\":\"Albay\",\"Level\":1,\"LocalizedType\":\"Province\",\"EnglishType\":\"Province\",\"CountryID\":\"PH\"},\"TimeZone\":{\"Code\":\"PHT\",\"Name\":\"Asia/Manila\",\"GmtOffset\":8.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":null},\"GeoPosition\":{\"Latitude\":13.261,\"Longitude\":124.022,\"Elevation\":{\"Metric\":{\"Value\":74.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":243.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":false,\"ParentCity\":{\"Key\":\"261782\",\"LocalizedName\":\"Rapu-Rapu\",\"EnglishName\":\"Rapu-Rapu\"},\"SupplementalAdminAreas\":[{\"Level\":2,\"LocalizedName\":\"Rapu-Rapu\",\"EnglishName\":\"Rapu-Rapu\"}],\"DataSets\":[\"Alerts\"]},{\"Version\":1,\"Key\":\"1069464\",\"Type\":\"City\",\"Rank\":600,\"LocalizedName\":\"Bilbao\",\"EnglishName\":\"Bilbao\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"NAM\",\"LocalizedName\":\"North America\",\"EnglishName\":\"North America\"},\"Country\":{\"ID\":\"MX\",\"LocalizedName\":\"Mexico\",\"EnglishName\":\"Mexico\"},\"AdministrativeArea\":{\"ID\":\"COA\",\"LocalizedName\":\"Coahuila\",\"EnglishName\":\"Coahuila\",\"Level\":1,\"LocalizedType\":\"State\",\"EnglishType\":\"State\",\"CountryID\":\"MX\"},\"TimeZone\":{\"Code\":\"CST\",\"Name\":\"America/Monterrey\",\"GmtOffset\":-6.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":\"2018-04-01T08:00:00Z\"},\"GeoPosition\":{\"Latitude\":25.833,\"Longitude\":-103.183,\"Elevation\":{\"Metric\":{\"Value\":1095.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":3591.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":false,\"SupplementalAdminAreas\":[],\"DataSets\":[\"ForecastConfidence\"]}";
		try {
			JSONObject jsonObj = new JSONObject(cityWeather);
			cityKey = (String) jsonObj.get("Key");
			System.out.println(jsonObj.get("LocalizedName") + ": "+jsonObj.get("Key")+"\n");


		} catch (JSONException e) {
			e.printStackTrace();
		}
		//		sabiendo la key de la city pido el weather
		weather = callURL("http://dataservice.accuweather.com/forecasts/v1/daily/5day/"+cityKey+"?apikey="+apiUserKey);

		//		weather = "{\"Headline\":{\"EffectiveDate\":\"2018-01-08T07:00:00+01:00\",\"EffectiveEpochDate\":1515391200,\"Severity\":5,\"Text\":\"Expect showers Monday\",\"Category\":\"rain\",\"EndDate\":\"2018-01-09T01:00:00+01:00\",\"EndEpochDate\":1515456000,\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/extended-weather-forecast/309382?lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?lang=en-us\"},\"DailyForecasts\":[{\"Date\":\"2018-01-08T07:00:00+01:00\",\"EpochDate\":1515391200,\"Temperature\":{\"Minimum\":{\"Value\":39.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":55.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":14,\"IconPhrase\":\"Partly sunny w/ showers\"},\"Night\":{\"Icon\":39,\"IconPhrase\":\"Partly cloudy w/ showers\"},\"Sources\":[\"AccuWeather\"],\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=1&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=1&lang=en-us\"},{\"Date\":\"2018-01-09T07:00:00+01:00\",\"EpochDate\":1515477600,\"Temperature\":{\"Minimum\":{\"Value\":43.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":55.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":12,\"IconPhrase\":\"Showers\"},\"Night\":{\"Icon\":18,\"IconPhrase\":\"Rain\"},\"Sources\":[\"AccuWeather\"],\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=2&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=2&lang=en-us\"},{\"Date\":\"2018-01-10T07:00:00+01:00\",\"EpochDate\":1515564000,\"Temperature\":{\"Minimum\":{\"Value\":44.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":57.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":12,\"IconPhrase\":\"Showers\"},\"Night\":{\"Icon\":12,\"IconPhrase\":\"Showers\"},\"Sources\":[\"AccuWeather\"],\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=3&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=3&lang=en-us\"},{\"Date\":\"2018-01-11T07:00:00+01:00\",\"EpochDate\":1515650400,\"Temperature\":{\"Minimum\":{\"Value\":44.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":50.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":18,\"IconPhrase\":\"Rain\"},\"Night\":{\"Icon\":12,\"IconPhrase\":\"Showers\"},\"Sources\":[\"AccuWeather\"],\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=4&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=4&lang=en-us\"},{\"Date\":\"2018-01-12T07:00:00+01:00\",\"EpochDate\":1515736800,\"Temperature\":{\"Minimum\":{\"Value\":45.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":56.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":4,\"IconPhrase\":\"Intermittent clouds\"},\"Night\":{\"Icon\":40,\"IconPhrase\":\"Mostly cloudy w/ showers\"},\"Sources\":[\"AccuWeather\"],\"MobileLink\":\"http://m.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=5&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/es/bilbao/309382/daily-weather-forecast/309382?day=5&lang=en-us\"}]}";
		System.out.println("\nOutput weather of "+cityName+": \n" + weather );

		try {
			JSONObject jsonObj = new JSONObject(weather);
			weatherData = getJSON(jsonObj);
			System.out.println("\nOutput weather of "+cityName+": \n" +  weatherData.toString());
			weatherObjecs = new ArrayList<>();
			//			pruebas:
			for (int i = 0; i < 5; i++) {
				System.out.println("weather of day: "+i);
				JSONObject jsonObj3 = new JSONObject( weatherData.get(i).toString());
				System.out.println(jsonObj3);


				Iterator<?> keys = jsonObj3.keys();
				weatherObjec = new WeatherVO();
				weatherObjec.setCity(cityName);
				while( keys.hasNext() ) {
					String key = (String)keys.next();
					if ( jsonObj3.get(key) instanceof JSONObject ) {
						//						System.out.println(key);

						if (key.contains("perat")) {
							JSONObject jsonObjTemperature = new JSONObject( jsonObj3.get(key).toString());
							//							System.out.println( "temp: " +jsonObjTemperature.toString());

							Iterator<?> keysTemperature = jsonObjTemperature.keys();

							while( keysTemperature.hasNext() ) {
								String keykeysTemperatureString = (String)keysTemperature.next();
								if (keykeysTemperatureString.contains("inimum")) {
									JSONObject jsonObjTemp2 = new JSONObject( jsonObjTemperature.get(keykeysTemperatureString).toString());

									//									System.out.println( "tempMin: " +jsonObjTemp2.toString());
									weatherObjec.setTemperatureMin((String)jsonObjTemp2.getString("Value"));
								}
								if (keykeysTemperatureString.contains("aximum")) {
									JSONObject jsonObjTemp2 = new JSONObject( jsonObjTemperature.get(keykeysTemperatureString).toString());

									//									System.out.println( "tempMax: " +jsonObjTemp2.toString());
									weatherObjec.setTemperatureMax((String)jsonObjTemp2.getString("Value"));

								}
							}

						}
						if (key.contains("ay")) {
							JSONObject jsonObjTemp = new JSONObject( jsonObj3.get(key).toString());
							//							System.out.println( "Day: " +jsonObjTemp);
							weatherObjec.setDayDesc((String)jsonObjTemp.get("IconPhrase"));

						}

						if (key.contains("ight")) {
							JSONObject jsonObjTemp = new JSONObject(  jsonObj3.get(key).toString());
							//							System.out.println( "Night: " +jsonObjTemp);
							weatherObjec.setNightDesc((String)jsonObjTemp.get("IconPhrase"));

						}
						if (key.contains("ate")) {
							JSONObject jsonObjTemp = new JSONObject(  jsonObj3.get(key).toString());
							//							System.out.println( "Date: " +jsonObjTemp);
							weatherObjec.setDate(jsonObjTemp.getString("Date"));

						}
						weatherObjec.setDate(jsonObj3.getString("Date"));


					}
					//										System.out.println("---------------------");
					//										System.out.println("tempMax: " + weatherObjec.getDate());

				}
				weatherObjecs.add(weatherObjec);
			}
			//fin pruebas

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weatherObjecs;
	}

	private static String callURL(String myURL) {


		System.out.println("Requeted URL: " + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {

			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {

				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {

					int cp;
					while ((cp = bufferedReader.read()) != -1) {

						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {

			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 

		return sb.toString();
	}

	private static JSONArray getJSON(JSONObject json ){

		Iterator<String> keys = json.keys();
		JSONArray value = null ;
		while(keys.hasNext()){
			String key = keys.next();
			try {
				value  = json.getJSONArray(key);
				for (int i = 0; i < value.length(); i++) {

					//					System.out.println(value.get(i));

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return value;

	}
}