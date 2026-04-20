package org.cs489.finalexam.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeaseCreateRequestDto(
        String leaseNumber,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal monthlyRentalRate
) {
}

