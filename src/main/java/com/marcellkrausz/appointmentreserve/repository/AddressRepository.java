package com.marcellkrausz.appointmentreserve.repository;

import com.marcellkrausz.appointmentreserve.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
