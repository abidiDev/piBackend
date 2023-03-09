package com.spring.pi.services;

import com.spring.pi.payload.request.OccsOfRealEastateRequest;
import com.spring.pi.payload.response.ObjectRassemblenceResponse;

import java.util.List;

public interface IuserService {
    public void addFavToAdsAndAssignToActor(Long actorId, Long adsId);
    public List<OccsOfRealEastateRequest>  getPertinentAds(Long actorId);

}
