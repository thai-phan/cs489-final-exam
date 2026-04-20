package org.cs489.finalexam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.cs489.finalexam.dto.LeaseCreateRequestDto;
import org.cs489.finalexam.dto.LeaseApartmentResponseDto;
import org.cs489.finalexam.dto.LeaseResponseDto;
import org.cs489.finalexam.dto.LeaseTenantResponseDto;
import org.cs489.finalexam.service.LeaseService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LeaseControllerTest {

    @Test
    void shouldReturnAllLeasesWithApartmentAndTenant() {
        LeaseService leaseService = mock(LeaseService.class);
        LeaseController leaseController = new LeaseController(leaseService);

        LeaseResponseDto lease = new LeaseResponseDto(
                1,
                "D0187-18775",
                LocalDate.of(2021, 10, 1),
                LocalDate.of(2022, 9, 30),
                new BigDecimal("2750.00"),
                new LeaseApartmentResponseDto(1, "W1100", "Bells Court", 14, 1050, 2),
                new LeaseTenantResponseDto(1, "Ben", "Klein", "(480) 123-1355", null)
        );

        when(leaseService.getAllLeases()).thenReturn(List.of(lease));

        List<LeaseResponseDto> result = leaseController.getAllLeases();

        assertEquals(1, result.size());
        assertEquals("D0187-18775", result.getFirst().leaseNumber());
        assertEquals("W1100", result.getFirst().apartment().apartmentNumber());
        assertEquals("Ben", result.getFirst().tenant().firstName());
    }

    @Test
    void shouldRegisterLeaseForApartmentAndTenant() {
        LeaseService leaseService = mock(LeaseService.class);
        LeaseController leaseController = new LeaseController(leaseService);

        LeaseCreateRequestDto request = new LeaseCreateRequestDto(
                "PJ692-74582",
                LocalDate.of(2026, 4, 20),
                LocalDate.of(2026, 4, 20),
                new BigDecimal("1255.00")
        );

        LeaseResponseDto created = new LeaseResponseDto(
                99,
                "PJ692-74582",
                LocalDate.of(2026, 4, 20),
                LocalDate.of(2026, 4, 20),
                new BigDecimal("1255.00"),
                new LeaseApartmentResponseDto(1, "W1100", "Bells Court", 14, 1050, 2),
                new LeaseTenantResponseDto(1, "Ben", "Klein", "(480) 123-1355", null)
        );

        when(leaseService.registerLease(1, 1, request)).thenReturn(created);

        ResponseEntity<LeaseResponseDto> response = leaseController.registerLease(1, 1, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("PJ692-74582", response.getBody().leaseNumber());
    }

    @Test
    void shouldReturnNotFoundWhenApartmentOrTenantDoesNotExist() {
        LeaseController leaseController = new LeaseController(mock(LeaseService.class));

        ResponseEntity<String> response = leaseController.handleNotFound(
                new NoSuchElementException("Apartment not found: 999")
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Apartment not found: 999", response.getBody());
    }
}

