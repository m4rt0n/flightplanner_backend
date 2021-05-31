package com.lanoga.flightplanner.service;

import java.util.List;

import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.model.Flight;

public interface IFlightCompanyService {
	public List<Company> getAllCompanies();

	public Company getCompanyById(long id) throws CompanyNotFoundException;

	public Company saveCompany(String companyCode, String companyName);

	public Company updateCompany(long id, String companyCode, String companyName) throws CompanyNotFoundException;

	public void deleteCompanyById(long id);

	public List<Flight> getFlightsByCompany(String companyName);

	public List<Flight> getFlightsByAirports(String departure, String arrival);

	List<Flight> getDirectFlights(List<Flight> fList, String departure, String arrival);

	List<Flight> getConnectedFlights(List<Flight> fList, String departure, String arrival);

	boolean isTransfer(Flight firstFlight, Flight secondFlight, String departure, String arrival);
}
