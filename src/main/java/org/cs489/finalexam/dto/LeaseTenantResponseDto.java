package org.cs489.finalexam.dto;

public record LeaseTenantResponseDto(
        Integer tenantId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}

