package net.vixim.twip.planner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Deliberately package private so can't be accessed by other nosey buggers.
@Repository
interface TripRepository extends CrudRepository<Trip, Long>
{
}
