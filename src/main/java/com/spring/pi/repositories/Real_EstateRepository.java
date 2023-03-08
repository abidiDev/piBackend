package com.spring.pi.repositories;

import com.spring.pi.entities.Real_Estate;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface Real_EstateRepository extends JpaRepository<Real_Estate, Long> {
   public List<Real_Estate>  findReal_EstateByLocalisation_City(String city);



 }

