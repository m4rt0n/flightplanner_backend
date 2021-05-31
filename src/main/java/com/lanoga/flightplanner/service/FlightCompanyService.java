package com.lanoga.flightplanner.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	@Override
	public Company getCompanyById(long id) throws CompanyNotFoundException {
		return cRepo.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
	}

	@Override
	public Company saveCompany(String companyCode, String companyName) {
		Company company = new Company();
		company.setCompanyName(companyName);
		company.setCompanyCode(companyCode);
		return cRepo.save(company);
	}

	@Override
	public Company updateCompany(long id, String companyCode, String companyName) throws CompanyNotFoundException {
		Company company = cRepo.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
		company.setCompanyName(companyName);
		company.setCompanyCode(companyCode);
		return cRepo.save(company);
	}

	@Override
	public void deleteCompanyById(long id) {
		List<Flight> relatedFlights = new ArrayList<>();
		fRepo.findAll().forEach(relatedFlights::add);
		List<Flight> flightsToDelete = relatedFlights.stream().filter(f -> f.getCompany().getId() == id)
				.collect(Collectors.toList());
		flightsToDelete.forEach(f -> fRepo.delete(f));
		cRepo.deleteById(id);
	}

	@Override
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

		List<Flight> directFlights = getDirectFlights(fList, departure, arrival);

		List<Flight> connectedFlights = getConnectedFlights(fList, departure, arrival);

		List<Flight> directAndConnectedFlights = Stream.concat(directFlights.stream(), connectedFlights.stream())
				.collect(Collectors.toList());

		return directAndConnectedFlights;
	}

	@Override
	public List<Flight> getDirectFlights(List<Flight> fList, String departure, String arrival) {
		return fList.stream().filter(f -> (f.getDepartureAirport().getAirportName().equals(departure)
				&& f.getArrivalAirport().getAirportName().equals(arrival))).collect(Collectors.toList());
	}

	@Override
	public List<Flight> getConnectedFlights(List<Flight> fList, String departure, String arrival) {
		List<Flight> listOfconnectedFlights = new ArrayList<>();
		for (int i = 0; i < fList.size(); i++) {
			Flight firstFlight = fList.get(i);
			for (int j = 0; j < fList.size(); j++) {
				Flight secondFlight = fList.get(j);
				if (isTransfer(firstFlight, secondFlight, departure, arrival)) {
					listOfconnectedFlights.add(firstFlight);
					listOfconnectedFlights.add(secondFlight);
				}
			}
		}
		return listOfconnectedFlights;
	}

	@Override
	public boolean isTransfer(Flight firstFlight, Flight secondFlight, String departure, String arrival) {
		return ((firstFlight != secondFlight)
				&& (firstFlight.getDepartureAirport().getAirportName().equals(departure)
						&& secondFlight.getArrivalAirport().getAirportName().equals(arrival))
				&& (firstFlight.getArrivalAirport().getAirportName()
						.equals(secondFlight.getDepartureAirport().getAirportName()))
				&& (firstFlight.getArrivalTime().compareTo(secondFlight.getDepartureTime()) < 0));
	}

}