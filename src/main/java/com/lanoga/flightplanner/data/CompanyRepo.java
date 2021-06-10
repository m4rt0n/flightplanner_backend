package com.lanoga.flightplanner.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanoga.flightplanner.model.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

}
