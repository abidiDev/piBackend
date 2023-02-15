package com.spring.pi.repositories;

import com.spring.pi.entities.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}