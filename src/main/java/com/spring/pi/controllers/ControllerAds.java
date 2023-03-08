package com.spring.pi.controllers;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Type;
import com.spring.pi.repositories.AdsRepository;
import com.spring.pi.repositories.Real_EstateRepository;
import com.spring.pi.services.IServiceAds;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
public class ControllerAds {
    IServiceAds iServiceAds;
    private final AdsRepository adsRepository;

    @PostMapping("/addAdsAndAsignToRealestate/{idRealestate}")
    @PreAuthorize("hasRole('USER') ")
    @ResponseBody
    public Ads addAdsAndAsignToRealestate(@RequestBody Ads ads ,@PathVariable long idRealestate ){
        return iServiceAds.addAdsAndAsignToRealestate( ads , idRealestate);
    }
    @GetMapping("/retriveAdsBytype/{type}")
    @PreAuthorize("hasRole('USER') ")
    public List<Ads> retriveAdsBytype(@PathVariable Type type){
        return iServiceAds.retriveAdsBytype(type);
    }

    @GetMapping("/sortAdsByRatingNumber")

    public List<Ads> sortAdsByRatingNumber(){
        return iServiceAds.sortAdsByRatingNumber();

    }
    @GetMapping("/sortAdsByRatingAndCommentNumber")

    public List<Ads> sortAdsByRatingAndCommentNumber(){
        return iServiceAds.sortAdsByRatingAndCommentNumber();

    }
    @PostMapping("/addClickToAd/{idAds}")
    public String addClickToAd(@PathVariable Long idAds) {
       return iServiceAds.addClickToAd(idAds);

    }
    @GetMapping("/getClicksForAd/{idAds}")

    public Integer getClicksForAd(@PathVariable Long idAds) {
        return iServiceAds.getClicksForAd(idAds);
    }
    @GetMapping("/search/{description}")
    public ResponseEntity<List<Ads>> searchByDescription(@PathVariable String description) {
        List<Ads> ads = iServiceAds.searchByDescription(description);
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/ads/StatisticByType")
    @ResponseBody
    public String countAdsByType() {
        List<Ads> ads = adsRepository.findAll();
        Map<Type, Long> adCountsByType = iServiceAds.countAdsByType(ads);
        int totalAds = ads.size(); // calcul du nombre total d'annonces

        StringBuilder result = new StringBuilder();
        for (Type type : adCountsByType.keySet()) {
            Long count = adCountsByType.get(type);
            double percentage = ((double) count / totalAds) * 100; // calcul du pourcentage
            result.append("The percentage of ads for type " + type + " is: " + percentage + "%\n");
        }
        return result.toString();
    }



}
