package com.lanoga.flightplanner.model;

public class FlightNotFoundException extends Exception {

	public FlightNotFoundException(String departure, String arrival) {
		super(String.format("No flights found: %s - %s", departure, arrival));
	}
}
