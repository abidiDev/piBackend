package com.spring.pi.repositories;

import com.spring.pi.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Real_EstateRepository extends JpaRepository<Real_Estate, Long> {
    @Query("SELECT h FROM House h")
    List<House> HousesList();

    @Query("SELECT b FROM Building b")
    List<Building> BuildingsList();

    @Query("SELECT p FROM Parking p")
    List<Parking> ParkingsList();
    @Query("SELECT g FROM Ground g")
    List<Ground> GroundsList();
    @Query("SELECT c FROM Commercial_Property c")
    List<Commercial_Property> CommercialPList();
    @Query("SELECT o FROM Office_Center o")
    List<Office_Center> OfficesCList();
}