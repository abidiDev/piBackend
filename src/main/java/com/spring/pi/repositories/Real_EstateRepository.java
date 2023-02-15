package com.spring.pi.repositories;

import com.spring.pi.entities.Real_Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Real_EstateRepository extends JpaRepository<Real_Estate, Long> {
}