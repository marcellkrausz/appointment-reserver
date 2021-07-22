package com.marcellkrausz.appointmentreserver.addresses;

import static org.assertj.core.api.Assertions.assertThat;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.models.City;
import com.marcellkrausz.appointmentreserver.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class AddressRepositoryIT {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void testPersist() {
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        Address address = new Address();
        address.setId(2L);
        address.setStreet("Próba");
        address.setHouseNumber(52);
        address.setCity(city);

        addressRepository.save(address);
        Set<Address> addresses = new HashSet<>();
        addressRepository.findAll().iterator().forEachRemaining(addresses::add);

        assertThat(addresses).extracting(Address::getStreet).contains("Próba");
    }
}
