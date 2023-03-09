package com.spring.pi.repositories;

import com.spring.pi.entities.Social_Service;
import com.spring.pi.entities.TypeSS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Social_ServiceRepository extends JpaRepository<Social_Service, Long> {
    public List<Social_Service> findSocial_ServiceByTypess(TypeSS typess);
    public List<Social_Service>findSocial_ServiceByLocalisationState(String state);
}