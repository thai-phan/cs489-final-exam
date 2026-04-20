package org.cs489.finalexam.repository;

import org.cs489.finalexam.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}

