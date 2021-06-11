package com.lanoga.flightplanner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

	private Long id;

	@NonNull
	private String companyCodeDTO;

	@NonNull
	private String companyNameDTO;

}
