package com.spring.pi.controllers;

import com.spring.pi.entities.Rating;
import com.spring.pi.services.IServiceRating;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
public class ControllerRating {
    IServiceRating iServiceRating;
    @PostMapping("/AddRatingAndasginToAds/{idAds}")
    @PreAuthorize("hasRole('USER') ")
    @ResponseBody
    public Rating AddRatingAndasginToAds(@PathVariable long idAds ,@RequestBody Rating rating){
        return iServiceRating.AddRatingAndasginToAds(idAds,rating);
    }
     @PostMapping("/CalculRatingNumber/{idAds}")
    @ResponseBody
   public int CalculRatingNumber(@PathVariable long idAds){
        return  iServiceRating.CalculRatingNumber(idAds);

    }



}
