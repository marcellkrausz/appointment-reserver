package com.marcellkrausz.appointmentreserver.repositories;

import com.marcellkrausz.appointmentreserver.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
