package com.spring.pi.services;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Rating;
import com.spring.pi.repositories.AdsRepository;
import com.spring.pi.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ServiceRating implements IServiceRating{
    RatingRepository ratingRepository;
    AdsRepository    adsRepository;

    @Override
    public Rating AddRatingAndasginToAds(long idAds, Rating rating) {
        Ads ads=adsRepository.findById(idAds).orElse(null);
        Rating r=ratingRepository.save(rating);
        r.setAds(ads);
        return ratingRepository.save(rating);
    }

    @Override
    public int CalculRatingNumber(long idAds) {
        Ads ads = adsRepository.findById(idAds).orElse(null);
        int nbrRating = 0;
        if (ads != null) {
            List<Rating> ratings = ratingRepository.findRatingByAds(ads);
            for (Rating r : ratings) {
                if (r.getNumber() > 1) {
                    nbrRating++;
                }
            }
        }
        return nbrRating;

    }

}
