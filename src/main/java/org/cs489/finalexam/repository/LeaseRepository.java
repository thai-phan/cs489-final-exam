package org.cs489.finalexam.repository;

import java.util.List;
import org.cs489.finalexam.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}

