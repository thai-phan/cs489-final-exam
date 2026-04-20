package org.cs489.finalexam.dto;

public record AddressResponseDto(
        Integer addressId,
        String apartmentNumber,
        String street,
        String city,
        String state,
        String zipCode
) {
}

