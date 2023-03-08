package com.spring.pi.services;

import com.spring.pi.entities.Ads;
import com.spring.pi.entities.Comment;
import com.spring.pi.entities.Rating;
import com.spring.pi.repositories.AdsRepository;
import com.spring.pi.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceComment implements IServiceComment{
     CommentRepository commentRepository;
     AdsRepository adsRepository;


    @Override
    public Comment AddCommentAndasginToAds(long idAds, Comment comment) {
        Ads ads=adsRepository.findById(idAds).orElse(null);
        Comment c=commentRepository.save(comment);
        c.setAds(ads);
        return commentRepository.save(comment);

    }
}
