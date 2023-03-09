package com.spring.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.pi.entities.ConstructionAgency;

public interface AgenceRepository extends JpaRepository<ConstructionAgency,Long> {
}
