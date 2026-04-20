package org.cs489.finalexam.service;

import java.util.List;
import org.cs489.finalexam.dto.AddressResponseDto;
import org.cs489.finalexam.dto.ApartmentResponseDto;
import org.cs489.finalexam.entity.Address;
import org.cs489.finalexam.entity.Apartment;
import org.cs489.finalexam.repository.ApartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<ApartmentResponseDto> getApartmentsByState(String state) {
        if (!StringUtils.hasText(state)) {
            throw new IllegalArgumentException("State is required");
        }

        return apartmentRepository.findAllByStateSortedWithAddress(state.trim())
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ApartmentResponseDto toDto(Apartment apartment) {
        Address address = apartment.getAddress();

        return new ApartmentResponseDto(
                apartment.getApartmentId(),
                apartment.getApartmentNumber(),
                apartment.getPropertyName(),
                apartment.getFloorNo(),
                apartment.getSize(),
                apartment.getNumberOfRooms(),
                new AddressResponseDto(
                        address.getAddressId(),
                        address.getApartmentNumber(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode()
                )
        );
    }
}

