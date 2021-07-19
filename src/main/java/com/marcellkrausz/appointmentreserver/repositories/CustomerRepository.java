package com.marcellkrausz.appointmentreserver.repositories;

import com.marcellkrausz.appointmentreserver.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
