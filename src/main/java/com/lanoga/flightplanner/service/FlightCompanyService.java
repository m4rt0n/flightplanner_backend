package com.lanoga.flightplanner.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanoga.flightplanner.data.AirportRepo;
import com.lanoga.flightplanner.data.CompanyRepo;
import com.lanoga.flightplanner.data.FlightRepo;
import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.model.Flight;

@Service
public class FlightCompanyService implements IFlightCompanyService {
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
		flights.sort(Comparator.comparing(Flight::getDepartureTime));
		return flights;
	}

	@Override
	public List<Flight> getFlightsByAirports(String departure, String arrival) {
		List<Flight> fList = new ArrayList<>();
		fRepo.findAll().forEach(fList::add);

		return getConnectedFlights(fList, departure, arrival);
		/*
		 * return fList.stream().filter(f ->
		 * (f.getAirports().get(0).getAirportName().equals(departure) &&
		 * f.getAirports().get(1).getAirportName().equals(arrival))).collect(Collectors.
		 * toList());
		 */
	}

	@Override
	public List<Flight> getConnectedFlights(List<Flight> fList, String departure, String arrival) {
		List<Flight> connectedFlights = new ArrayList<>();
		for (int i = 0; i < fList.size(); i++) {
			Flight firstFlight = fList.get(i);

			for (int j = 0; j < fList.size(); j++) {
				Flight secondFlight = fList.get(j);

			}

		}

		return connectedFlights;

	}
}