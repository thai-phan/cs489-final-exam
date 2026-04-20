package org.cs489.finalexam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.cs489.finalexam.dto.LeaseCreateRequestDto;
import org.cs489.finalexam.dto.LeaseResponseDto;
import org.cs489.finalexam.entity.Address;
import org.cs489.finalexam.entity.Apartment;
import org.cs489.finalexam.entity.Lease;
import org.cs489.finalexam.entity.Tenant;
import org.cs489.finalexam.repository.ApartmentRepository;
import org.cs489.finalexam.repository.LeaseRepository;
import org.cs489.finalexam.repository.TenantRepository;
import org.junit.jupiter.api.Test;

class LeaseServiceTest {

    @Test
    void shouldMapLeasesToDtoIncludingApartmentAndTenant() {
        LeaseRepository leaseRepository = mock(LeaseRepository.class);
        ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
        TenantRepository tenantRepository = mock(TenantRepository.class);
        LeaseService leaseService = new LeaseService(leaseRepository, apartmentRepository, tenantRepository);

        Address address = new Address();
        address.setAddressId(1);
        address.setApartmentNumber("W1100");
        address.setStreet("40 W Burlington Avenue");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZipCode("66543");

        Apartment apartment = new Apartment();
        apartment.setApartmentId(1);
        apartment.setApartmentNumber("W1100");
        apartment.setPropertyName("Bells Court");
        apartment.setFloorNo(14);
        apartment.setSize(1050);
        apartment.setNumberOfRooms(2);
        apartment.setAddress(address);

        Tenant tenant = new Tenant();
        tenant.setTenantId(1);
        tenant.setFirstName("Ben");
        tenant.setLastName("Klein");
        tenant.setPhoneNumber("(480) 123-1355");

        Lease lease = new Lease();
        lease.setLeaseId(1);
        lease.setLeaseNumber("D0187-18775");
        lease.setStartDate(LocalDate.of(2021, 10, 1));
        lease.setEndDate(LocalDate.of(2022, 9, 30));
        lease.setMonthlyRentalRate(new BigDecimal("2750.00"));
        lease.setApartment(apartment);
        lease.setTenant(tenant);

        when(leaseRepository.findAllWithApartmentAndTenantOrderByLeaseNumberAsc()).thenReturn(List.of(lease));

        List<LeaseResponseDto> result = leaseService.getAllLeases();

        assertEquals(1, result.size());
        assertEquals("D0187-18775", result.getFirst().leaseNumber());
        assertEquals("Bells Court", result.getFirst().apartment().propertyName());
        assertEquals("Klein", result.getFirst().tenant().lastName());
    }

    @Test
    void shouldRegisterLeaseForApartmentAndTenant() {
        LeaseRepository leaseRepository = mock(LeaseRepository.class);
        ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
        TenantRepository tenantRepository = mock(TenantRepository.class);
        LeaseService leaseService = new LeaseService(leaseRepository, apartmentRepository, tenantRepository);

        Apartment apartment = new Apartment();
        apartment.setApartmentId(1);
        apartment.setApartmentNumber("W1100");
        apartment.setPropertyName("Bells Court");
        apartment.setFloorNo(14);
        apartment.setSize(1050);
        apartment.setNumberOfRooms(2);

        Tenant tenant = new Tenant();
        tenant.setTenantId(1);
        tenant.setFirstName("Ben");
        tenant.setLastName("Klein");
        tenant.setPhoneNumber("(480) 123-1355");

        LeaseCreateRequestDto request = new LeaseCreateRequestDto(
                "PJ692-74582",
                LocalDate.of(2026, 4, 20),
                LocalDate.of(2026, 4, 20),
                new BigDecimal("1255.00")
        );

        when(apartmentRepository.findById(1)).thenReturn(Optional.of(apartment));
        when(tenantRepository.findById(1)).thenReturn(Optional.of(tenant));
        when(leaseRepository.existsByLeaseNumberIgnoreCase("PJ692-74582")).thenReturn(false);
        when(leaseRepository.save(org.mockito.ArgumentMatchers.any(Lease.class))).thenAnswer(invocation -> {
            Lease saved = invocation.getArgument(0);
            saved.setLeaseId(99);
            return saved;
        });

        LeaseResponseDto created = leaseService.registerLease(1, 1, request);

        assertEquals(99, created.leaseId());
        assertEquals("PJ692-74582", created.leaseNumber());
        assertEquals("W1100", created.apartment().apartmentNumber());
        assertEquals("Ben", created.tenant().firstName());
    }

    @Test
    void shouldRejectInvalidDateRangeWhenRegisteringLease() {
        LeaseRepository leaseRepository = mock(LeaseRepository.class);
        ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
        TenantRepository tenantRepository = mock(TenantRepository.class);
        LeaseService leaseService = new LeaseService(leaseRepository, apartmentRepository, tenantRepository);

        LeaseCreateRequestDto request = new LeaseCreateRequestDto(
                "PJ692-74582",
                LocalDate.of(2026, 4, 21),
                LocalDate.of(2026, 4, 20),
                new BigDecimal("1255.00")
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> leaseService.registerLease(1, 1, request)
        );

        assertEquals("End date must be on or after start date", exception.getMessage());
    }
}

