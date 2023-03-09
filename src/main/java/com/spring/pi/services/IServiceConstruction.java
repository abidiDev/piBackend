package com.spring.pi.services;


import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Actor_construction;
import com.spring.pi.payload.request.AddConstructionRequeste;


import java.util.List;

public interface IServiceConstruction {

    public List<Object> search(Long surfaceterrain, Long HouseSurface, Integer nbrofroom, Boolean piscine, Boolean jardin,
                               Integer nbreEtage, String lieux, Long prix);
    public Float getPr(Actor_construction a);
    public List<Actor_construction> add(AddConstructionRequeste actorConstruc);

}
