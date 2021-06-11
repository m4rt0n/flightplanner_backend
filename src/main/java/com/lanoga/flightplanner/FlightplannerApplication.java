package com.lanoga.flightplanner;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
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
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			LocalDateTime now = LocalDateTime.of(2021, 5, 21, 10, 10);

			Company qatar = new Company("QA", "Qatar");
			Company lufthansa = new Company("LH", "Lufthansa");
			Company ryanair = new Company("RA", "Ryanair");
			cRepo.saveAll(Arrays.asList(qatar, lufthansa, ryanair));

			Airport moscow = new Airport("Moscow");
			Airport budapest = new Airport("Budapest");
			Airport berlin = new Airport("Berlin");
			Airport tbilisi = new Airport("Tbilisi");
			aRepo.saveAll(Arrays.asList(moscow, budapest, berlin, tbilisi));

			Flight moscowToBudapest = new Flight(qatar, moscow, budapest, now, now.plusHours(1));
			Flight moscowToBerlin = new Flight(qatar, moscow, berlin, now.plusHours(3), now.plusHours(4));
			Flight berlinToBudapest = new Flight(ryanair, berlin, budapest, now.plusHours(6), now.plusHours(7));
			Flight moscowToTbilisi = new Flight(lufthansa, moscow, tbilisi, now.plusHours(3), now.plusHours(4));
			Flight tbilisiToBudapest = new Flight(qatar, tbilisi, budapest, now.plusHours(6), now.plusHours(7));
			fRepo.saveAll(Arrays.asList(moscowToBudapest, moscowToBerlin, berlinToBudapest, moscowToTbilisi,
					tbilisiToBudapest));

		};
	}

}
