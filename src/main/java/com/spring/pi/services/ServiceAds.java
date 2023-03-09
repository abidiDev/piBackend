package com.spring.pi.services;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Real_Estate;
import com.spring.pi.entities.Type;
import com.spring.pi.repositories.AdsRepository;
import com.spring.pi.repositories.RatingRepository;
import com.spring.pi.repositories.Real_EstateRepository;
import com.spring.pi.repositories.Social_ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ServiceAds implements IServiceAds {
    AdsRepository adsRepository;
    RatingRepository ratingRepository;
    Real_EstateRepository realEstateRepository;
    Social_ServiceRepository socialServiceRepository;

    private final CacheManager cacheManager = new ConcurrentMapCacheManager("adClicks");

    @Override
    public Ads addAdsAndAsignToRealestate(Ads ads, long idRealestate) {
        Real_Estate realEstate = realEstateRepository.findById(idRealestate).orElse(null);
        Ads a = adsRepository.save(ads);
        a.setReal_Estate(realEstate);
        return adsRepository.save(ads);

    }

    @Override
    public List<Ads> retriveAdsBytype(Type type) {
        return adsRepository.findAdsByType(type);
    }

    @Override
    public List<Ads> sortAdsByRatingNumber() {
        /*
        List<Ads> ads = adsRepository.findAll();
        Collections.sort(ads, (a1, a2) -> Integer.compare(a2.getRating().size(), a1.getRating().size()));
        */

        return adsRepository.sortAdsByRatingNumber();
    }

    @Override
    public List<Ads> sortAdsByRatingAndCommentNumber() {
        return adsRepository.sortAdsByRatingAndCommentNumber();
    }

    @Override
    public String addClickToAd(Long idAds) {
        Cache cache = cacheManager.getCache("adClicks");
        Integer clicks = cache.get(idAds, Integer.class);
        if (clicks == null) {
            clicks = 0;
        }
        clicks++;
        cache.put(idAds, clicks);
        return "You clicked on this ad";

    }

    @Override
    public Integer getClicksForAd(Long idAds) {
        Cache cache = cacheManager.getCache("adClicks");
        Integer clicks = cache.get(idAds, Integer.class);
        if (clicks == null) {
            clicks = 0;
        }
        return clicks;
    }

    @Override
    public List<Ads> searchByDescription(String description) {
        return adsRepository.findByDescriptionContainingIgnoreCase(description);

    }

   @Override

  public Map<Type, Long> countAdsByType(List<Ads> ads) {
      Map<Type, Long> adCountsByType = new HashMap<>();
      for (Ads ad : ads) {
          Type type = ad.getType();
          if (!adCountsByType.containsKey(type)) {
              adCountsByType.put(type, 0L);
          }
          adCountsByType.put(type, adCountsByType.get(type) + 1);
      }
      return adCountsByType;
  }


}







