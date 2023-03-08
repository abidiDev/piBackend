package com.spring.pi.repositories;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Type;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Long> {
     List<Ads> findAdsByType(Type type);

      @Query("SELECT a FROM Ads a LEFT JOIN FETCH a.rating r GROUP BY a.id ORDER BY COUNT(r.id) DESC")
      List<Ads> sortAdsByRatingNumber();


      @Query("SELECT a FROM Ads a LEFT JOIN FETCH a.rating r LEFT JOIN FETCH a.comments c GROUP BY a.id ORDER BY (COUNT(r.id) + COUNT(c.id)) DESC")
      List<Ads> sortAdsByRatingAndCommentNumber();

    List<Ads> findByDescriptionContainingIgnoreCase(String description);

}


