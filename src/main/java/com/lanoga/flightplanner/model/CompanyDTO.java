package com.lanoga.flightplanner.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyDTOId;

	@NonNull
	private String companyDTOCode;

	@NonNull
	private String companyDTOName;

}
