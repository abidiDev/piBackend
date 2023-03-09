package com.spring.pi.controllers;

import com.spring.pi.entities.House;
import com.spring.pi.entities.Real_Estate;
import com.spring.pi.entities.Social_Service;
import com.spring.pi.entities.TypeSS;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;
import com.spring.pi.payload.response.SocialServiceResponce;
import com.spring.pi.services.IServiceSocialSer;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
public class ControllerSocSer {
    IServiceSocialSer iServiceSocialSer;
    @GetMapping("/getServiceByTypeSS/{typess}")

    public List<Social_Service> getServiceByTypeSS(TypeSS typess){
        return iServiceSocialSer.getServiceByTypeSS(typess);
    }
    @PostMapping("/addServiceAndAsignToRealestate/{idRealEstate}")
    //@PreAuthorize("hasRole('USER') ")
    @ResponseBody
    public SocialServiceResponce addServiceAndAsignToRealestate(@RequestBody RealestateRequest realestateRequest , @PathVariable long idRealEstate){
        return iServiceSocialSer.addServiceAndAsignToRealestate(realestateRequest , idRealEstate);

    }

}

