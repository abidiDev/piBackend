package com.spring.pi.repositories;

import com.spring.pi.entities.Appartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppartmentRepository extends JpaRepository<Appartment, Long> {
}