package org.cs489.finalexam.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class EntityModelTest {

    @Test
    void shouldBuildEntitiesAndRelationships() {
        Address address = new Address();
        address.setApartmentNumber("W1100");
        address.setStreet("40 W Burlington Avenue");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZipCode("66543");

        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("W1100");
        apartment.setPropertyName("Bells Court");
        apartment.setFloorNo(14);
        apartment.setSize(1050);
        apartment.setNumberOfRooms(2);
        apartment.setAddress(address);

        Tenant tenant = new Tenant();
        tenant.setFirstName("Ben");
        tenant.setLastName("Klein");
        tenant.setPhoneNumber("(480) 123-1355");
        tenant.setEmail("ben@example.com");

        Lease lease = new Lease();
        lease.setLeaseNumber("D0187-18775");
        lease.setStartDate(LocalDate.of(2021, 10, 1));
        lease.setEndDate(LocalDate.of(2022, 9, 30));
        lease.setMonthlyRentalRate(new BigDecimal("2750.00"));
        lease.setApartment(apartment);
        lease.setTenant(tenant);

        apartment.getLeases().add(lease);
        tenant.getLeases().add(lease);

        assertNotNull(apartment.getAddress());
        assertEquals("D0187-18775", lease.getLeaseNumber());
        assertEquals(1, apartment.getLeases().size());
        assertEquals(1, tenant.getLeases().size());
    }
}

