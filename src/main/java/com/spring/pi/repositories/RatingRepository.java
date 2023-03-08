package com.spring.pi.repositories;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
     List<Rating> findRatingByAds(Ads ads);
  //  public int countByAdsAAndNumberGreaterThan(long idAds , long number);
}