package com.spring.pi.services;

import com.spring.pi.entities.Social_Service;
import com.spring.pi.entities.TypeSS;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;
import com.spring.pi.payload.response.SocialServiceResponce;

import java.util.List;

public interface IServiceSocialSer {
    public List<Social_Service> getServiceByTypeSS(TypeSS typess);
    public SocialServiceResponce addServiceAndAsignToRealestate(RealestateRequest realestateRequest , long idRealEstate);

}
