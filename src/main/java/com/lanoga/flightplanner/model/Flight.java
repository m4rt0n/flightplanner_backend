package com.lanoga.flightplanner.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "flight")
@Data
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "airports_flights", joinColumns = @JoinColumn(name = "airport_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"))
	private List<Airport> airports;

	@NonNull
	@Column(name = "departure_date")
	private LocalDateTime departureDate;

	@NonNull
	@Column(name = "arrival_date")
	private LocalDateTime arrivalTime;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

}
