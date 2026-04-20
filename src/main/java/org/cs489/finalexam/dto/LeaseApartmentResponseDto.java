package org.cs489.finalexam.dto;

public record LeaseApartmentResponseDto(
        Integer apartmentId,
        String apartmentNumber,
        String propertyName,
        Integer floorNo,
        Integer size,
        Integer numberOfRooms
) {
}

