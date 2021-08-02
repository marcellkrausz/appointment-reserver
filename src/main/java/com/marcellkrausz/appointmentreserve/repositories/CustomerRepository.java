package com.marcellkrausz.appointmentreserve.repositories;

import com.marcellkrausz.appointmentreserve.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
