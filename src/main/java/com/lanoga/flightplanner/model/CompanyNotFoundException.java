package com.lanoga.flightplanner.model;

public class CompanyNotFoundException extends Exception {

	public CompanyNotFoundException(long id) {
		super(String.format("Company is not found: %s", id));
	}

}
