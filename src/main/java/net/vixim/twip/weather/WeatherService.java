package net.vixim.twip.weather;

import java.util.List;

public interface WeatherService
{
  List<CityForecast> getCityForecast(String city);
}
