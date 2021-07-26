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
 * A Trip consisting of multiple stops, stored for a finite time.
 */
@Entity
@Table(name = "trip")
public class Trip
{
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  // Time beyond which we no longer need to store this trip
  @Column
  private Timestamp keepUntilTs;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "trip_id")
  private Collection<TripStop> stops = new ArrayList<>();

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Timestamp getKeepUntilTs()
  {
    return keepUntilTs;
  }

  public void setKeepUntilTs(Timestamp keepUntilTs)
  {
    this.keepUntilTs = keepUntilTs;
  }

  public Collection<TripStop> getStops()
  {
    return stops;
  }

  public void setStops(Collection<TripStop> stops)
  {
    this.stops = stops;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Trip trip = (Trip) o;
    return Objects.equals(id, trip.id);
  }

  @Override public int hashCode()
  {
    return Objects.hash(id);
  }

  @Override public String toString()
  {
    return "Trip{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", keepUntilTs=" + keepUntilTs +
        ", stops=" + stops +
        '}';
  }
}
