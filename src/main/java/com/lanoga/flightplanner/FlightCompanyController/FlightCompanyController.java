package com.lanoga.flightplanner.FlightCompanyController;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
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
import com.lanoga.flightplanner.model.CompanyDTO;
import com.lanoga.flightplanner.model.CompanyNotFoundException;
import com.lanoga.flightplanner.model.Flight;
import com.lanoga.flightplanner.service.IFlightCompanyService;

@CrossOrigin(origins = { "http://localhost:3002" })
@RestController
@RequestMapping(path = "/company")
public class FlightCompanyController {
	@Autowired
	IFlightCompanyService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/getall")
	public List<CompanyDTO> getAllCompanies() {
		List<Company> comps = service.getAllCompanies();
		return comps.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@GetMapping("/getbyid/{id}")
	public CompanyDTO getCompanyById(@PathVariable("id") long id) throws CompanyNotFoundException {
		return convertToDTO(service.getCompanyById(id));
	}

	// , consumes = MediaType.APPLICATION_JSON_VALUE
	@PostMapping(value = "/save")
	public CompanyDTO saveCompany(@RequestBody CompanyDTO compDTO) throws ParseException, CompanyNotFoundException {
		Company comp = convertToDAO(compDTO);
		Company compCreated = service.saveCompany(comp);
		return convertToDTO(compCreated);
	}

	@PutMapping("/update/{id}")
	public CompanyDTO updateCompany(@PathVariable("id") long id, @RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name) throws CompanyNotFoundException {
		return convertToDTO(service.updateCompany(id, code, name));
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

	private CompanyDTO convertToDTO(Company comp) {
		CompanyDTO compDTO = modelMapper.map(comp, CompanyDTO.class);
		compDTO.setCompanyCodeDTO(comp.getCompanyCode());
		compDTO.setCompanyNameDTO(comp.getCompanyName());
		return compDTO;
	}

	private Company convertToDAO(CompanyDTO compDTO) throws ParseException, CompanyNotFoundException {
		Company comp = modelMapper.map(compDTO, Company.class);
		comp.setCompanyCode(compDTO.getCompanyCodeDTO());
		comp.setCompanyName(compDTO.getCompanyNameDTO());
		if (compDTO.getId() != null) {
			Company oldComp = service.getCompanyById(compDTO.getId());
			comp.setId(oldComp.getId());
		}
		return comp;
	}
}