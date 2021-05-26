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

	public void addEntities() {
		LocalDateTime now = LocalDateTime.of(2021, 5, 21, 10, 10);

		Company qatar = new Company("Qatar");
		Company lufthansa = new Company("Lufthansa");
		Company ryanair = new Company("Ryanair");
		cRepo.saveAll(Arrays.asList(qatar, lufthansa, ryanair));

		Airport moscow = new Airport("Moscow");
		Airport budapest = new Airport("Budapest");
		Airport berlin = new Airport("Berlin");
		aRepo.saveAll(Arrays.asList(moscow, budapest, berlin));

		Flight moscowToBudapest = new Flight((Arrays.asList(moscow, budapest)), now, (now.plusHours(1)), qatar);
		Flight moscowToBerlin = new Flight((Arrays.asList(moscow, berlin)), (now.plusHours(1)), (now.plusHours(2)),
				lufthansa);
		Flight berlinToBudapest = new Flight((Arrays.asList(berlin, budapest)), (now.plusHours(3)), (now.plusHours(4)),
				ryanair);
		fRepo.saveAll(Arrays.asList(moscowToBudapest, moscowToBerlin, berlinToBudapest));
	}

	public List<Company> getAllCompanies() {
		List<Company> cList = new ArrayList<>();
		cRepo.findAll().forEach(cList::add);
		return cList;
	}

	public Company getCompanyById(long id) throws CompanyNotFoundException {
		return cRepo.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
	}

	public Company saveCompany(String companyName) {
		Company company = new Company(companyName);
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
}
