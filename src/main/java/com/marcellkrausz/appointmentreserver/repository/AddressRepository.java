package com.marcellkrausz.appointmentreserver.repository;

import com.marcellkrausz.appointmentreserver.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
