package com.spring.pi.controllers;

import com.spring.pi.payload.request.OccsOfRealEastateRequest;
import com.spring.pi.services.IuserService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    IuserService iuserService;
    @GetMapping("/addFavToAdsAndAssignToActor/{actorId}/{adsId}")

    public void addFavToAdsAndAssignToActor(@PathVariable Long actorId,@PathVariable Long adsId){
        iuserService.addFavToAdsAndAssignToActor(actorId,adsId);

    }
    @GetMapping("/getPertinentAds/{actorId}")

    public List<OccsOfRealEastateRequest> getPertinentAds(@PathVariable Long actorId){
        return iuserService.getPertinentAds(actorId);

    }


}
