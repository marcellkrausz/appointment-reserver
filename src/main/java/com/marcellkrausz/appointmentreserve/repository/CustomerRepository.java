package com.marcellkrausz.appointmentreserve.repository;

import com.marcellkrausz.appointmentreserve.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
