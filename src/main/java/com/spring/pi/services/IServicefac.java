package com.spring.pi.services;

import com.spring.pi.entities.Comment;
import com.spring.pi.entities.ForumPublication;
import com.spring.pi.entities.Reactions;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.List;

public interface IServicefac {
    @Async
    String SendSms(String Phone, String message);

    public Comment addcommentandassigntoPublicationanduser(Comment c, Long idforum, Long iduser) throws IOException;
    public Comment updatecomment (Comment comment,Long idcomm);
    public void deleteComment(Long id);

    public int getnbrecommentdansunforum(Long forumid);
    //////////////////////forum//////////////////////////
    public ForumPublication addforumandassigntouser(ForumPublication forum, Long userid) ;
    public ForumPublication updateforum (ForumPublication forum, Long idforum);
    public List<ForumPublication> getForumpub();
    public void deleteforum (Long id );
    public Long nbrelikes(Long idforum);
    public Long nbredislikes(Long idforum);

    public String checkIfUserShouldBeBlocked(Long userid);

    public Reactions ajouteretassocierunereactionaforumetuser(Reactions r, Long forum, Long iduser);


    public int tentatifbadword(Long iduser) ;


    public Reactions reactionupdate(Reactions r, Long id);

    public List<ForumPublication> rechercherParMot(String mot);

    public String  assignBadgeToUser(Long user);
}
