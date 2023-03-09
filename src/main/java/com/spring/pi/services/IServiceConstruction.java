package com.spring.pi.services;


import com.spring.pi.entities.Actor_construction;


import java.util.List;

public interface IServiceConstruction {

    public List<Object> search(Long surfaceterrain, Long HouseSurface, Integer nbrofroom, Boolean piscine, Boolean jardin,
                               Integer nbreEtage, String lieux, Long prix);
    public Float getPr(Actor_construction a);

}
