package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.repositories.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@Service
@Slf4j
public class servicefac implements IServicefac{
    @Autowired
    SwearWordService swearWordService;
    @Autowired

    NotificationRepository notificationRepository;
    @Autowired

    CommentaireRep commentRepository;
    @Autowired

    ReactionRepository repository;
    @Autowired

    ForumPublicationRepository forumPublicationRepository;
    @Autowired
    ActorRepository userRepository;
    @Value("e857976851bb8276a4f56727e4268629")
    private  String Service_TWILIO_AUTH_TOKEN;
    @Value("ACa4c676fc1aecd1858d5ee6ccb0f61750")
    private  String Service_TWILIO_ACCOUNT_SID;
    @Async
    @Override
    public String SendSms(String Phone, String message) {
        Twilio.init(Service_TWILIO_ACCOUNT_SID, Service_TWILIO_AUTH_TOKEN);
        Message.creator(new PhoneNumber(Phone),
                new PhoneNumber("+21696907231"), message).create();//15673392016

        log.info("Sms Send");
        return "Message sent successfully";
    }

    @Override
    @Transactional
    public Comment addcommentandassigntoPublicationanduser(Comment c, Long idforum, Long iduser) throws IOException {
        commentRepository.save(c);
        ForumPublication forumpub = forumPublicationRepository.findById(idforum).orElse(null);
        Actor user=userRepository.findById(iduser).orElse(null);
        forumpub.getComments().add(c);
        c.setUser(user);
        c.setCreatedAt(LocalDate.now());

        return commentRepository.save(c);
    }

    @Override
    public Comment updatecomment(Comment comment, Long idcomm) {
        Comment savedcomm= commentRepository.findById(idcomm).orElse(null);
        savedcomm.setContent(comment.getContent());
        savedcomm.setCreatedAt(LocalDate.now());

        return    commentRepository.save(savedcomm);     }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public int getnbrecommentdansunforum(Long forumid) {
        ForumPublication f=forumPublicationRepository.findById(forumid).orElse(null);
     return    f.getComments().size();    }


    @Override
    @Transactional

    public ForumPublication addforumandassigntouser(ForumPublication forum, Long userid) {
       Actor user = userRepository.findById(userid).orElse(null);
        forum.setDate_pub(LocalDate.now());

        forum.setUsersf(user);
        return forumPublicationRepository.save(forum);
    }

    @Override
    public ForumPublication updateforum(ForumPublication forum, Long idforum) {
        ForumPublication savedforum= forumPublicationRepository.findById(idforum).orElse(null);
        savedforum.setObject(forum.getObject());
        savedforum.setTopic(forum.getTopic());
        savedforum.setDate_pub(LocalDate.now());

        savedforum.setObject(savedforum.getObject());
        return    forumPublicationRepository.save(savedforum);    }

    @Override
    public List<ForumPublication> getForumpub() {
        return forumPublicationRepository.findAll();      }

    @Override
    public void deleteforum(Long id) {
        forumPublicationRepository.deleteById(id);

    }

    @Override
    public Long nbrelikes(Long idforum) {
        return repository.countReactionssurunforum(TypeReaction.like,idforum);
    }

    @Override
    public Long nbredislikes(Long idforum) {
        return repository.countReactionssurunforum(TypeReaction.dislike,idforum);     }

    @Override
    public String checkIfUserShouldBeBlocked(Long userid) {
        int tentatif=tentatifbadword(userid);
        if(tentatif>=3){
            Actor user=userRepository.findById(userid).orElse(null);
            user.setBlocked(true);
            userRepository.save(user);
            return "this user should be blocked with  tentatif number : "+""+tentatif;
        }
        return null;
    }

    @Override
    public Reactions ajouteretassocierunereactionaforumetuser(Reactions r, Long forum, Long iduser) {
        ForumPublication forumpub = forumPublicationRepository.findById(forum).orElse(null);
        Actor c=userRepository.findById(iduser).orElse(null);
        Reactions existingReaction =  repository.findByUserAndForumpublication(c,forumpub);
        if(existingReaction ==null)
        {
            r.setForumpublication(forumpub);
            r.setUser(c);
            return repository.save(r);
        }else
            return existingReaction;    }

    @Override
    public int tentatifbadword(Long iduser) {
        return notificationRepository.findByUtilisateur_Id(iduser).size();
    }

    @Override
    public Reactions reactionupdate(Reactions r, Long id) {
        return null;
    }

    @Override
    public List<ForumPublication> rechercherParMot(String mot) {
        return forumPublicationRepository.findByObjectContainingIgnoreCaseOrTopicContainingIgnoreCaseOrCommentsContentContainingIgnoreCase(mot,mot,mot);
    }


    @Override
    public String assignBadgeToUser(Long user) {
        Actor actor =userRepository.findById(user).orElse(null);
        int activitypoints=0;
        int numforumadded=forumPublicationRepository.findByUsersfId(user).size();
        int reactionsadded=repository.findByUserId(user).size();
        int numcommentadded=commentRepository.findByUserId(user).size();
        activitypoints += numcommentadded * 2;
        activitypoints += numforumadded * 3;
        activitypoints += reactionsadded * 1;
        String Badge;

        if (activitypoints >= 100) {
            Badge="Gold";
        } else if (activitypoints >= 50) {
            Badge="Silver";
        } else if (activitypoints >= 20) {
            Badge="Bronze";
        } else {
            Badge="Begginer";
        }
        actor.setBadge(Badge);
        userRepository.save(actor);
        return Badge;}
}

