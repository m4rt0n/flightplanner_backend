package com.lanoga.flightplanner.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lanoga.flightplanner.model.Airport;

@Repository
public interface AirportRepo extends CrudRepository<Airport, Long> {

}
