package com.lanoga.flightplanner.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "airport")
@Data
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@Column(name = "airport_name")
	private String airportName;

	@ManyToMany(mappedBy = "airports")
	private List<Flight> flights;

}
