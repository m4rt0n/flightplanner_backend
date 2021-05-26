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
import lombok.NonNull;

@Entity
@Table(name = "company")
@Data
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@Column(name = "company_name")
	private String companyName;

	// , cascade = { CascadeType.PERSIST, CascadeType.MERGE }
	@OneToMany(mappedBy = "company")
	private List<Flight> flights;

}
