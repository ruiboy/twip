package net.vixim.twip.weather.openweathermap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.vixim.twip.weather.CityForecast;
import net.vixim.twip.weather.openweathermap.json.ForecastResponse;

class JsonUtil
{
  private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  private JsonUtil()
  {
  }

  /**
   * Parse weather forecast JSON as per <a href="https://openweathermap.org/forecast5#JSON">this</a> as at 26/7/2021
   * into a summary format.
   */
  public static List<CityForecast> convertCityForecastJson(String json)
  {
    List<CityForecast> cfs = new ArrayList<>();

    try
    {
      ForecastResponse root = mapper.readValue(json, ForecastResponse.class);

      String cityName = root.getCity().getName();
      String countryCode = root.getCity().getCountry();
      for (net.vixim.twip.weather.openweathermap.json.List item : root.getList())
      {
        CityForecast cf = new CityForecast();
        cf.setCityName(cityName);
        cf.setCountryCode(countryCode);
        cf.setTimestamp(new Timestamp(item.getDt() * 1000)); // API returns epoch time in seconds, java wants millis
        cf.setTemperature(Math.round(item.getMain().getTemp()));
        cf.setWeatherMain(!item.getWeather().isEmpty() ? item.getWeather().get(0).getMain() : null);

        cfs.add(cf);
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not parse JSON: " + json, e);
    }

    return cfs;
  }
}
