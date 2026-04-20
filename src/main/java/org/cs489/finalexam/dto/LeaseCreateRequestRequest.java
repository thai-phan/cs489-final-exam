package org.cs489.finalexam.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeaseCreateRequestRequest(
        String leaseNumber,
        String startDate,
        String endDate,
        BigDecimal monthlyRentalRate
) {
}

