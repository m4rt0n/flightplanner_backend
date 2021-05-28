package com.lanoga.flightplanner.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanoga.flightplanner.data.AirportRepo;
import com.lanoga.flightplanner.data.CompanyRepo;
import com.lanoga.flightplanner.data.FlightRepo;
import com.lanoga.flightplanner.model.Airport;
import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.model.Flight;

@Service
public class FlightCompanyService {
	@Autowired
	CompanyRepo cRepo;
	@Autowired
	FlightRepo fRepo;
	@Autowired
	AirportRepo aRepo;

	public List<Company> getAllCompanies() {
		List<Company> cList = new ArrayList<>();
		cRepo.findAll().forEach(cList::add);
		return cList;
	}

	public Company getCompanyById(long id) throws CompanyNotFoundException {
		return cRepo.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
	}

	public Company saveCompany(String companyName) {
		Company company = new Company();
		company.setCompanyName(companyName);
		return cRepo.save(company);
	}

	public Company updateCompany(long id, String companyName) throws CompanyNotFoundException {
		Company company = cRepo.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
		company.setCompanyName(companyName);
		return cRepo.save(company);
	}

	public void deleteCompanyById(long id) {
		cRepo.deleteById(id);
	}

	public List<Flight> getFlightsByCompany(String companyName) {
		List<Company> cList = new ArrayList<>();
		cRepo.findAll().forEach(cList::add);
		Optional<Company> company = cList.stream().filter(c -> c.getCompanyName().equals(companyName)).findFirst();
		List<Flight> flights = company.get().getFlights();
		flights.sort(Comparator.comparing(Flight::getDepartureDate));
		return flights;
	}

	public void addEntities() {
		LocalDateTime now = LocalDateTime.of(2021, 5, 21, 10, 10);

		Company qatar = new Company();
		qatar.setCompanyName("Qatar");
		Company lufthansa = new Company();
		lufthansa.setCompanyName("Lufthansa");
		Company ryanair = new Company();
		ryanair.setCompanyName("Ryanair");
		cRepo.saveAll(Arrays.asList(qatar, lufthansa, ryanair));

		Airport moscow = new Airport();
		moscow.setAirportName("Moscow");
		Airport budapest = new Airport();
		budapest.setAirportName("Budapest");
		Airport berlin = new Airport();
		berlin.setAirportName("Berlin");
		aRepo.saveAll(Arrays.asList(moscow, budapest, berlin));

		Flight moscowToBudapest = new Flight();
		moscowToBudapest.setAirports(Arrays.asList(moscow, budapest));
		moscowToBudapest.setDepartureDate(now);
		moscowToBudapest.setArrivalTime(now.plusHours(1));
		moscowToBudapest.setCompany(qatar);

		Flight moscowToBerlin = new Flight();
		moscowToBerlin.setAirports(Arrays.asList(moscow, berlin));
		moscowToBerlin.setDepartureDate(now.plusHours(1));
		moscowToBerlin.setArrivalTime(now.plusHours(2));
		moscowToBerlin.setCompany(lufthansa);

		Flight berlinToBudapest = new Flight();
		berlinToBudapest.setAirports(Arrays.asList(berlin, budapest));
		berlinToBudapest.setDepartureDate(now.plusHours(3));
		berlinToBudapest.setArrivalTime(now.plusHours(4));
		berlinToBudapest.setCompany(ryanair);

		fRepo.saveAll(Arrays.asList(moscowToBudapest, moscowToBerlin, berlinToBudapest));
	}

}