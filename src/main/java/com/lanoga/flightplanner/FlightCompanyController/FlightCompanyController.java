package com.lanoga.flightplanner.FlightCompanyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.service.FlightCompanyService;

@RestController
@RequestMapping(path = "/company")
public class FlightCompanyController {
	@Autowired
	FlightCompanyService service;

	@PostMapping("/add")
	public void addEntities() {
		service.addEntities();
	}

	@GetMapping("/getall")
	public void getAllCompanies() {
		service.getAllCompanies();
	}

	@GetMapping("/getbyid/{id}")
	public Company getCompanyById(@PathVariable("id") long id) throws CompanyNotFoundException {
		return service.getCompanyById(id);
	}

	@PostMapping("/save")
	public Company saveCompany(@PathVariable("name") String name) {
		return service.saveCompany(name);
	}

	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable("id") long id, @PathVariable("name") String name)
			throws CompanyNotFoundException {
		return service.updateCompany(id, name);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCompanyById(@PathVariable("id") long id) {
		service.deleteCompanyById(id);
	}
}
