package org.cs489.finalexam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(nullable = false, length = 20)
    private String apartmentNumber;

    @Column(nullable = false, length = 120)
    private String street;

    @Column(nullable = false, length = 60)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 10)
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Apartment apartment;
}

