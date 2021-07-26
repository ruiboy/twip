package net.vixim.twip.weather.openweathermap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import net.vixim.twip.weather.CityForecast;
import net.vixim.twip.weather.WeatherService;

/**
 * WeatherService impl using openweathermap API.
 */
@Service
class OpenWeatherMapWeatherService implements WeatherService
{
  private static final String CITY_FORECAST_URL =
      "http://api.openweathermap.org/data/2.5/forecast?q={city}&APPID={appId}&units={units}";

  @Value("${twip.weather.cityForecast.appId:NO-APPID-CONFIGURED}")
  private String citeForecastAppId;

  private RestTemplate restTemplate;

  @Autowired
  public OpenWeatherMapWeatherService(RestTemplateBuilder restTemplateBuilder)
  {
    this.restTemplate = restTemplateBuilder.build();
  }

  public List<CityForecast> getCityForecast(String city)
  {
    Assert.notNull(city, "city is null");
    String json = makeCityForecastRequest(city);
    return JsonUtil.convertCityForecastJson(json);
  }

  private String makeCityForecastRequest(String city)
  {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("city", city);
    parameters.put("appId", citeForecastAppId);
    parameters.put("units", "metric");

    ResponseEntity<String> response = restTemplate.getForEntity(CITY_FORECAST_URL, String.class, parameters);

    if (response.getStatusCode() == HttpStatus.ACCEPTED.OK)
    {
      return response.getBody();
    }
    else
    {
      throw new RuntimeException("Unable to get forecast for city: " + city + ", response: " + response.getBody());
    }
  }
}
