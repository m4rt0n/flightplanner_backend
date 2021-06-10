package com.lanoga.flightplanner.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@NonNull
	@JsonManagedReference(value = "flight-departure")
	@ManyToOne
	@JoinColumn(name = "flight_departure_id")
	private Airport departureAirport;

	@NonNull
	@JsonManagedReference(value = "flight-arrival")
	@ManyToOne
	@JoinColumn(name = "flight_arrival_id")
	private Airport arrivalAirport;

	@NonNull
	@Column(name = "departure_date")
	private LocalDateTime departureTime;

	@NonNull
	@Column(name = "arrival_date")
	private LocalDateTime arrivalTime;

}