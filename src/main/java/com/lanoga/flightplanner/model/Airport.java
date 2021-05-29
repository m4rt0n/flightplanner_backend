package com.lanoga.flightplanner.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "airport")
@Data
@NoArgsConstructor
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	@Column(name = "airport_name")
	private String airportName;

	@OneToMany(mappedBy = "departureAirport")
	private List<Flight> departures;

	@OneToMany(mappedBy = "arrivalAirport")
	private List<Flight> arrivals;
}
