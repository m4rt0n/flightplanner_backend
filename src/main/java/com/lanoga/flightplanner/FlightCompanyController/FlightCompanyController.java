package com.lanoga.flightplanner.FlightCompanyController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.model.Flight;
import com.lanoga.flightplanner.service.IFlightCompanyService;

@CrossOrigin(origins = { "http://localhost:3002" })
@RestController
@RequestMapping(path = "/company")
public class FlightCompanyController {
	@Autowired
	IFlightCompanyService service;

	@GetMapping("/getall")
	public List<Company> getAllCompanies() {
		return service.getAllCompanies();
	}

	@GetMapping("/getbyid/{id}")
	public Company getCompanyById(@PathVariable("id") long id) throws CompanyNotFoundException {
		return service.getCompanyById(id);
	}

	// , consumes = MediaType.APPLICATION_JSON_VALUE
	@PostMapping(value = "/save")
	public Company saveCompany(@RequestBody Company comp) {
		return service.saveCompany(comp);
	}

	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable("id") long id, @RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name) throws CompanyNotFoundException {
		return service.updateCompany(id, code, name);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCompanyById(@PathVariable("id") long id) {
		service.deleteCompanyById(id);
	}

	@GetMapping("/getallflights")
	public List<Flight> getAllFlightsByCompany(@RequestParam(value = "name") String name)
			throws CompanyNotFoundException {
		return service.getFlightsByCompany(name);
	}

	@PostMapping("/getflightsbyairports")
	public List<Flight> getFlightsByAirports(@RequestParam(value = "departure") String departure,
			@RequestParam(value = "arrival") String arrival) {
		return service.getFlightsByAirports(departure, arrival);
	}
}