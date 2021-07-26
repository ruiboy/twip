package net.vixim.twip.planner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * One stop on a trip.  Can have multiple weather forecasts attached (for different times in the stop).
 */
@Entity
@Table(name = "trip_stop")
public class TripStop
{
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String cityName;

  @Column
  private String countryCode;

  @Column
  private Timestamp arriveTs;

  @Column
  private Timestamp departTs;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "trip_stop_id")
  private Collection<Forecast> forecasts = new ArrayList<>();

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

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

  public Timestamp getArriveTs()
  {
    return arriveTs;
  }

  public void setArriveTs(Timestamp arriveTs)
  {
    this.arriveTs = arriveTs;
  }

  public Timestamp getDepartTs()
  {
    return departTs;
  }

  public void setDepartTs(Timestamp departTs)
  {
    this.departTs = departTs;
  }

  public Collection<Forecast> getForecasts()
  {
    return forecasts;
  }

  public void setForecasts(Collection<Forecast> forecasts)
  {
    this.forecasts = forecasts;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TripStop tripStop = (TripStop) o;
    return Objects.equals(id, tripStop.id);
  }

  @Override public int hashCode()
  {
    return Objects.hash(id);
  }

  @Override public String toString()
  {
    return "TripStop{" +
        "id=" + id +
        ", cityName='" + cityName + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", arriveTs=" + arriveTs +
        ", departTs=" + departTs +
        ", forecasts=" + forecasts +
        '}';
  }
}
