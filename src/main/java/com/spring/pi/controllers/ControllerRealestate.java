package com.spring.pi.controllers;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;
import com.spring.pi.services.IServiceRealestate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
public class ControllerRealestate {
    IServiceRealestate iServiceRealestate;

    @PostMapping("/AddRealestateAdvanced")
    @ResponseBody
    public RealestateRespone AddRealestateAdvanced(@RequestBody RealestateRequest realestateRequest){
        return iServiceRealestate.AddRealestateAdvanced(realestateRequest);}

    @GetMapping("/getRealestateByLocationCity/{city}")
   // @PreAuthorize("hasRole('USER') ")
    public List<Real_Estate> getRealestateByLocationCity(@PathVariable String city){
        return iServiceRealestate.getRealestateByLocationCity(city);
    }
    @GetMapping("getrealestate/{id}")
    public Real_Estate getRealEstateById(@PathVariable Long id) {
        return iServiceRealestate.getRealEstateById(id);
    }
    @GetMapping("getRealestateByPrice/{priceMin}/{priceMax}")
  //  @PreAuthorize("hasRole('USER') ")
    public List<Real_Estate>  getRealestateByPrice(@PathVariable float priceMin, @PathVariable float priceMax){
        return iServiceRealestate.getRealestateByPrice(priceMin,priceMax);
    }
    @GetMapping("/paymentplan/{idRealestate}")
//    @PreAuthorize("hasRole('USER') ")
    public ResponseEntity<String> getPaymentPlanById(@PathVariable Long idRealestate) {
        String message = iServiceRealestate.calculatePaymentPlanById(idRealestate);
        return ResponseEntity.ok(message);
    }


}