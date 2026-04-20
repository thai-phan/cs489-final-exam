package org.cs489.finalexam.repository;

import java.util.List;
import org.cs489.finalexam.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query("""
            SELECT a
            FROM Apartment a
            JOIN FETCH a.address ad
            WHERE LOWER(ad.state) = LOWER(:state)
            ORDER BY a.propertyName ASC, a.size ASC
            """)
    List<Apartment> findAllByStateSortedWithAddress(@Param("state") String state);
}

