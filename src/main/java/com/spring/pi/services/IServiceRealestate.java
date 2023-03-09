package com.spring.pi.services;


import com.spring.pi.entities.*;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;

import java.util.List;

public interface IServiceRealestate {
    public List<Real_Estate> getRealestateByLocationCity(String city);
    public RealestateRespone AddRealestateAdvanced(RealestateRequest realestateRequest);
    public List<Real_Estate> getRealestateByPrice(float priceMin,float priceMax);
    public Real_Estate getRealEstateById(Long id);
   public String calculatePaymentPlanById(Long idRealestate)   ;


}
