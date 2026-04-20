package org.cs489.finalexam.dto;

import java.math.BigDecimal;

public record TotalLeaseRevenueResponseDto(
        String state,
        BigDecimal totalRevenue
) {
}

