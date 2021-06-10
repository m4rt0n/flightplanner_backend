package com.lanoga.flightplanner.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanoga.flightplanner.model.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Long> {

}
