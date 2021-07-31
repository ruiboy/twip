package net.vixim.twip.planner;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Deliberately package private so can't be accessed by other nosey buggers.
// Usually would wrap this in a Service before exposing to the Controller, but being a bit lazy today.
@Repository
interface TripRepository extends CrudRepository<Trip, Long>
{
  Optional<Trip> getFirstByName(String name);
}
