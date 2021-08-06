package com.marcellkrausz.appointmentreserve.repositories;

import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyCarteRepository extends JpaRepository<BeautyCare, Long> {
}
