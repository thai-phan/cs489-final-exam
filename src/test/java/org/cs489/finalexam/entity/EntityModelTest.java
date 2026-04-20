package org.cs489.finalexam.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class EntityModelTest {

    @Test
    void shouldBuildEntitiesWithLombokBuilder() {
        Address address = Address.builder()
                .apartmentNumber("W1100")
                .street("40 W Burlington Avenue")
                .city("Chicago")
                .state("IL")
                .zipCode("66543")
                .build();

        Apartment apartment = Apartment.builder()
                .apartmentNumber("W1100")
                .propertyName("Bells Court")
                .floorNo(14)
                .size(1050)
                .numberOfRooms(2)
                .address(address)
                .build();

        Tenant tenant = Tenant.builder()
                .firstName("Ben")
                .lastName("Klein")
                .phoneNumber("(480) 123-1355")
                .email("ben@example.com")
                .build();

        Lease lease = Lease.builder()
                .leaseNumber("D0187-18775")
                .startDate(LocalDate.of(2021, 10, 1))
                .endDate(LocalDate.of(2022, 9, 30))
                .monthlyRentalRate(new BigDecimal("2750.00"))
                .apartment(apartment)
                .tenant(tenant)
                .build();

        apartment.getLeases().add(lease);
        tenant.getLeases().add(lease);

        assertNotNull(apartment.getAddress());
        assertEquals("D0187-18775", lease.getLeaseNumber());
        assertEquals(1, apartment.getLeases().size());
        assertEquals(1, tenant.getLeases().size());
    }
}

