package com.lanoga.flightplanner.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lanoga.flightplanner.model.Flight;

@Repository
public interface FlightRepo extends CrudRepository<Flight, Long> {

}
