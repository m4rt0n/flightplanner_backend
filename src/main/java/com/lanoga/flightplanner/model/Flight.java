package com.lanoga.flightplanner.model;

import java.time.LocalDateTime;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "airports_flights", joinColumns = @JoinColumn(name = "airport_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"))
	private List<Airport> airports;

	@Column(name = "departure_date")
	private LocalDateTime departureDate;

	@Column(name = "arrival_date")
	private LocalDateTime arrivalTime;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

}