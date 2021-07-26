package net.vixim.twip.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
public class TripController
{
  private TripRepository tripRepository;

  @Autowired
  public TripController(TripRepository tripRepository)
  {
    this.tripRepository = tripRepository;
  }

  @GetMapping("/trip/{id}")
  public Trip getTrip(@PathVariable Long id)
  {
    return tripRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No trip exists with id " + id));
  }
}
