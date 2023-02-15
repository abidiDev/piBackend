package com.spring.pi.repositories;

import com.spring.pi.entities.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ads, Long> {
}