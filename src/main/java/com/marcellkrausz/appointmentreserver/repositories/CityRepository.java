package com.marcellkrausz.appointmentreserver.repositories;

import com.marcellkrausz.appointmentreserver.models.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
}
