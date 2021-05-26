package com.lanoga.flightplanner.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lanoga.flightplanner.model.Company;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Long> {

}
