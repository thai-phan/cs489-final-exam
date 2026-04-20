package org.cs489.finalexam.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeaseResponseDto(
        Integer leaseId,
        String leaseNumber,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal monthlyRentalRate,
        LeaseApartmentResponseDto apartment,
        LeaseTenantResponseDto tenant
) {
}

