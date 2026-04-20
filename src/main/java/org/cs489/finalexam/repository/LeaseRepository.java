package org.cs489.finalexam.repository;

import java.util.List;
import org.cs489.finalexam.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaseRepository extends JpaRepository<Lease, Integer> {

    @Query("""
            SELECT l
            FROM Lease l
            JOIN FETCH l.apartment a
            JOIN FETCH l.tenant t
            ORDER BY l.leaseNumber ASC
            """)
    List<Lease> findAllWithApartmentAndTenantOrderByLeaseNumberAsc();

    boolean existsByLeaseNumberIgnoreCase(String leaseNumber);

    @Query("""
            SELECT l
            FROM Lease l
            JOIN l.apartment a
            JOIN a.address ad
            WHERE LOWER(ad.state) = LOWER(:state)
            """)
    List<Lease> findAllByState(@Param("state") String state);
}

