package com.spring.pi.services;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Type;

import java.util.List;
import java.util.Map;

public interface IServiceAds {

    public List<Ads> retriveAdsBytype(Type type);
    public Ads addAdsAndAsignToRealestate(Ads ads, long idRealestate);
    public List<Ads> sortAdsByRatingNumber();
    public List<Ads> sortAdsByRatingAndCommentNumber();
    public String addClickToAd(Long idAds) ;
    public Integer getClicksForAd(Long idAds) ;
    public List<Ads> searchByDescription(String description);
    public Map<Type, Long> countAdsByType(List<Ads> ads);

    }
