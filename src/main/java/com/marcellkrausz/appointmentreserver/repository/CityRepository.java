package com.marcellkrausz.appointmentreserver.repository;

import com.marcellkrausz.appointmentreserver.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
}
