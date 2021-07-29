package net.vixim.twip.weather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController
{
  private WeatherService weatherService;

  @Autowired
  public WeatherController(WeatherService weatherService)
  {
    this.weatherService = weatherService;
  }

  @GetMapping("/cityForecast/{city}")
  public List<CityForecast> getCityForecast(@PathVariable String city)
  {
    return weatherService.getCityForecast(city);
  }
}
