package com.lanoga.flightplanner;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lanoga.flightplanner.data.AirportRepo;
import com.lanoga.flightplanner.data.CompanyRepo;
import com.lanoga.flightplanner.data.FlightRepo;
import com.lanoga.flightplanner.model.Airport;
import com.lanoga.flightplanner.model.Company;
import com.lanoga.flightplanner.model.Flight;

@SpringBootApplication
public class FlightplannerApplication {
	@Autowired
	CompanyRepo cRepo;
	@Autowired
	FlightRepo fRepo;
	@Autowired
	AirportRepo aRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlightplannerApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
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
			moscowToBudapest.setDepartureAirport(moscow);
			moscowToBudapest.setArrivalAirport(budapest);
			moscowToBudapest.setDepartureTime(now);
			moscowToBudapest.setArrivalTime(now.plusHours(1));
			moscowToBudapest.setCompany(qatar);

			Flight moscowToBerlin = new Flight();
			moscowToBerlin.setDepartureAirport(moscow);
			moscowToBerlin.setArrivalAirport(berlin);
			moscowToBerlin.setDepartureTime(now.plusHours(3));
			moscowToBerlin.setArrivalTime(now.plusHours(4));
			moscowToBerlin.setCompany(qatar);

			Flight berlinToBudapest = new Flight();
			berlinToBudapest.setDepartureAirport(berlin);
			berlinToBudapest.setArrivalAirport(budapest);
			berlinToBudapest.setDepartureTime(now.plusHours(6));
			berlinToBudapest.setArrivalTime(now.plusHours(7));
			berlinToBudapest.setCompany(ryanair);

			fRepo.saveAll(Arrays.asList(moscowToBudapest, moscowToBerlin, berlinToBudapest));
		};
	}

}
