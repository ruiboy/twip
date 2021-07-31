package net.vixim.twip.weather.openweathermap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import net.vixim.twip.weather.CityForecast;
import net.vixim.twip.weather.WeatherService;

/**
 * WeatherService impl using openweathermap API.
 * Requires system property openweathermap.appId.
 */
@Service
class OpenWeatherMapWeatherService implements WeatherService
{
  private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapWeatherService.class);

  private static final String CITY_FORECAST_URL =
      "http://api.openweathermap.org/data/2.5/forecast?q={city}&APPID={appId}&units={units}";

  private RestTemplate restTemplate;
  private String appId;

  @Autowired
  public OpenWeatherMapWeatherService(RestTemplateBuilder restTemplateBuilder)
  {
    this.restTemplate = restTemplateBuilder.build();
    this.appId = System.getProperty("openweathermap.appId");
    Assert.hasText(appId, "System property openweathermap.appId not set");
  }

  @Cacheable("cityForecasts")
  public List<CityForecast> getCityForecast(String city)
  {
    Assert.notNull(city, "city is null");
    String json = makeCityForecastRequest(city);
    logger.debug("Loaded forecast for city {}, {}", city, json);
    return JsonUtil.convertCityForecastJson(json);
  }

  private String makeCityForecastRequest(String city)
  {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("city", city);
    parameters.put("appId", appId);
    parameters.put("units", "metric");

    try
    {
      return restTemplate.getForObject(CITY_FORECAST_URL, String.class, parameters);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Unable to get forecast for city: " + city, e);
    }
  }
}
