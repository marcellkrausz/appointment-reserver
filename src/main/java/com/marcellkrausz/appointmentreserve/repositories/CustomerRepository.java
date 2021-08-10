package com.marcellkrausz.appointmentreserve.repositories;

import com.marcellkrausz.appointmentreserve.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
