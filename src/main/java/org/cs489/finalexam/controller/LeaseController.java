package org.cs489.finalexam.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.cs489.finalexam.dto.LeaseCreateRequestDto;
import org.cs489.finalexam.dto.LeaseResponseDto;
import org.cs489.finalexam.service.LeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LeaseController {

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping("/leases")
    public List<LeaseResponseDto> getAllLeases() {
        return leaseService.getAllLeases();
    }

    @PostMapping("/apartments/{apartmentId}/tenants/{tenantId}/leases")
    public ResponseEntity<LeaseResponseDto> registerLease(
            @PathVariable Integer apartmentId,
            @PathVariable Integer tenantId,
            @RequestBody LeaseCreateRequestDto request
    ) {
        LeaseResponseDto createdLease = leaseService.registerLease(apartmentId, tenantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLease);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

