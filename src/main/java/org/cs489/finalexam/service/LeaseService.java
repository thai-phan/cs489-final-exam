package org.cs489.finalexam.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.cs489.finalexam.dto.LeaseCreateRequestDto;
import org.cs489.finalexam.dto.LeaseApartmentResponseDto;
import org.cs489.finalexam.dto.LeaseResponseDto;
import org.cs489.finalexam.dto.LeaseTenantResponseDto;
import org.cs489.finalexam.entity.Apartment;
import org.cs489.finalexam.entity.Lease;
import org.cs489.finalexam.entity.Tenant;
import org.cs489.finalexam.repository.ApartmentRepository;
import org.cs489.finalexam.repository.LeaseRepository;
import org.cs489.finalexam.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final ApartmentRepository apartmentRepository;
    private final TenantRepository tenantRepository;

    public LeaseService(
            LeaseRepository leaseRepository,
            ApartmentRepository apartmentRepository,
            TenantRepository tenantRepository
    ) {
        this.leaseRepository = leaseRepository;
        this.apartmentRepository = apartmentRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<LeaseResponseDto> getAllLeases() {
        return leaseRepository.findAllWithApartmentAndTenantOrderByLeaseNumberAsc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public LeaseResponseDto registerLease(Integer apartmentId, Integer tenantId, LeaseCreateRequestDto request) {
        validateRequest(request);

        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NoSuchElementException("Apartment not found: " + apartmentId));
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new NoSuchElementException("Tenant not found: " + tenantId));

        String normalizedLeaseNumber = request.leaseNumber().trim();
        if (leaseRepository.existsByLeaseNumberIgnoreCase(normalizedLeaseNumber)) {
            throw new IllegalArgumentException("Lease number already exists: " + normalizedLeaseNumber);
        }

        Lease lease = new Lease();
        lease.setLeaseNumber(normalizedLeaseNumber);
        lease.setStartDate(request.startDate());
        lease.setEndDate(request.endDate());
        lease.setMonthlyRentalRate(request.monthlyRentalRate());
        lease.setApartment(apartment);
        lease.setTenant(tenant);

        return toDto(leaseRepository.save(lease));
    }

    private void validateRequest(LeaseCreateRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (!StringUtils.hasText(request.leaseNumber())) {
            throw new IllegalArgumentException("Lease number is required");
        }
        if (request.startDate() == null || request.endDate() == null) {
            throw new IllegalArgumentException("Start date and end date are required");
        }
        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("End date must be on or after start date");
        }
        if (request.monthlyRentalRate() == null || request.monthlyRentalRate().signum() <= 0) {
            throw new IllegalArgumentException("Monthly rental rate must be greater than zero");
        }
    }

    private LeaseResponseDto toDto(Lease lease) {
        Apartment apartment = lease.getApartment();
        Tenant tenant = lease.getTenant();

        return new LeaseResponseDto(
                lease.getLeaseId(),
                lease.getLeaseNumber(),
                lease.getStartDate(),
                lease.getEndDate(),
                lease.getMonthlyRentalRate(),
                new LeaseApartmentResponseDto(
                        apartment.getApartmentId(),
                        apartment.getApartmentNumber(),
                        apartment.getPropertyName(),
                        apartment.getFloorNo(),
                        apartment.getSize(),
                        apartment.getNumberOfRooms()
                ),
                new LeaseTenantResponseDto(
                        tenant.getTenantId(),
                        tenant.getFirstName(),
                        tenant.getLastName(),
                        tenant.getPhoneNumber(),
                        tenant.getEmail()
                )
        );
    }
}

