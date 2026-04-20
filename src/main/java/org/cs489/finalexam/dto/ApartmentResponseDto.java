package org.cs489.finalexam.dto;

public record ApartmentResponseDto(
        Integer apartmentId,
        String apartmentNumber,
        String propertyName,
        Integer floorNo,
        Integer size,
        Integer numberOfRooms,
        AddressResponseDto address
) {
}

