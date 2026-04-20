package org.cs489.finalexam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.cs489.finalexam.dto.AddressResponseDto;
import org.cs489.finalexam.dto.ApartmentResponseDto;
import org.cs489.finalexam.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApartmentControllerTest {

    @Test
    void shouldReturnApartmentsByStateWithAddress() {
        ApartmentService apartmentService = mock(ApartmentService.class);
        ApartmentController apartmentController = new ApartmentController(apartmentService);

        ApartmentResponseDto dto = new ApartmentResponseDto(
                1,
                "W1100",
                "Bells Court",
                14,
                1050,
                2,
                new AddressResponseDto(1, "W1100", "40 W Burlington Avenue", "Chicago", "IL", "66543")
        );

        when(apartmentService.getApartmentsByState("IL")).thenReturn(List.of(dto));

        List<ApartmentResponseDto> response = apartmentController.getApartmentsByState("IL");

        assertEquals(1, response.size());
        assertEquals("Bells Court", response.getFirst().propertyName());
        assertEquals("40 W Burlington Avenue", response.getFirst().address().street());
    }

    @Test
    void shouldReturnBadRequestMessageForInvalidState() {
        ApartmentController apartmentController = new ApartmentController(mock(ApartmentService.class));

        ResponseEntity<String> response = apartmentController.handleInvalidRequest(
                new IllegalArgumentException("State is required")
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("State is required", response.getBody());
    }
}

