package com.marcellkrausz.appointmentreserve.repositories;

import com.marcellkrausz.appointmentreserve.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
