package com.spring.pi.services;

import com.spring.pi.entities.Rating;

public interface IServiceRating {

    public Rating AddRatingAndasginToAds(long idAds , Rating rating);
    public int CalculRatingNumber(long idAds);

}
