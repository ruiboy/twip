package net.vixim.twip.planner;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Brief weather forecast.
 */
@Entity
@Table(name = "forecast")
public class Forecast
{
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Timestamp forTs;

  // temp in celsius rounded to nearest int
  @Column
  private int temperature;

  // Group of weather parameters (Rain, Snow, Extreme etc.)
  @Column
  private String weatherMain;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Timestamp getForTs()
  {
    return forTs;
  }

  public void setForTs(Timestamp forTs)
  {
    this.forTs = forTs;
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
    Forecast forecast = (Forecast) o;
    return Objects.equals(id, forecast.id);
  }

  @Override public int hashCode()
  {
    return Objects.hash(id);
  }

  @Override public String toString()
  {
    return "Forecast{" +
        "id=" + id +
        ", forTs=" + forTs +
        ", temperature=" + temperature +
        ", weatherMain='" + weatherMain + '\'' +
        '}';
  }
}
