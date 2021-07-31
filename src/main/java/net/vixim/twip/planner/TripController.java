package net.vixim.twip.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planner")
public class TripController
{
  private TripRepository tripRepository;

  @Autowired
  public TripController(TripRepository tripRepository)
  {
    this.tripRepository = tripRepository;
  }

  @GetMapping("/trip/{name}")
  public Trip getTrip(@PathVariable String name)
  {
    return tripRepository.getFirstByName(name)
        .orElseThrow(() -> new RuntimeException("No trip exists with name " + name));
  }

  @PutMapping("/trip/{name}")
  public Trip saveTrip(@PathVariable String name, @RequestBody Trip trip)
  {
    trip.setName(name);
    return tripRepository.save(trip);
  }
}
