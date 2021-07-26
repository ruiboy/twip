package net.vixim.twip.weather;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Brief weather forecast for city/time.
 */
public class CityForecast
{
  private String cityName;
  private String countryCode;
  private Timestamp timestamp;
  // temp in celsius rounded to nearest int
  private int temperature;
  // Group of weather parameters (Rain, Snow, Extreme etc.)
  private String weatherMain;

  public String getCityName()
  {
    return cityName;
  }

  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }

  public String getCountryCode()
  {
    return countryCode;
  }

  public void setCountryCode(String countryCode)
  {
    this.countryCode = countryCode;
  }

  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }

  public int getTemperature()
  {
    return temperature;
  }

  public void setTemperature(int temperature)
  {
    this.temperature = temperature;
  }

  public String getWeatherMain()
  {
    return weatherMain;
  }

  public void setWeatherMain(String weatherMain)
  {
    this.weatherMain = weatherMain;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CityForecast that = (CityForecast) o;
    return Objects.equals(cityName, that.cityName) && Objects.equals(countryCode, that.countryCode)
        && Objects.equals(timestamp, that.timestamp);
  }

  @Override public int hashCode()
  {
    return Objects.hash(cityName, countryCode, timestamp);
  }

  @Override public String toString()
  {
    return "CityForecast{" +
        "cityName='" + cityName + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", timestamp=" + timestamp +
        ", temperature=" + temperature +
        ", weatherMain='" + weatherMain + '\'' +
        '}';
  }
}
