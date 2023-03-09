package com.spring.pi.controllers;

import com.spring.pi.entities.*;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.CommentaireRep;
import com.spring.pi.repositories.NotificationRepository;
import com.spring.pi.repositories.ReactionRepository;
import com.spring.pi.services.IServicefac;
import com.spring.pi.services.SwearWordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/FAC")
public class ControllerForumandComment {
    @Autowired
    IServicefac iService;
    @Autowired

    ReactionRepository repository;
    @Autowired

    NotificationRepository notificationRepository ;
    @Autowired

    ActorRepository userRepository;
    @Autowired

    CommentaireRep commentaireRep;
    @Autowired

    SwearWordService swearWordService;

//////////////////////////////////////////////////////COMMENT///////////////////////////////////////////////////////////




    @PostMapping("ajoutercomm/{idf}/{iduser}")
    public ResponseEntity<Comment> addcommentandassigntoPublication(@RequestBody Comment c, @PathVariable("idf") Long idforum, @PathVariable("iduser") Long iduser) throws IOException {

        Notification nf = new Notification();
        Actor user=userRepository.findById(iduser).orElse(null);
        if(containsbadword(c.getContent())){
            nf.setCreatedAt(LocalDate.now());
            nf.setMessage("using bad word in the comment");
            notificationRepository.save(nf);
            String text=censorText(c.getContent());
            c.setContent(text);
            c.setIdcomm(c.getIdcomm());

            nf.setUtilisateur(user);

            Comment addedcomment=iService.addcommentandassigntoPublicationanduser(c,idforum,iduser);

            return ResponseEntity.ok(addedcomment);
        }

        Comment addedcomment=iService.addcommentandassigntoPublicationanduser(c,idforum,iduser);
        iService.SendSms("+21694745442","a new comment was added");
        return ResponseEntity.ok(addedcomment);
    }

    public String censorText(String text) throws IOException {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (containsbadword(word)) {
                // replace bad word with asterisks
                String asterisks = "";
                for (int i = 0; i < word.length(); i++) {
                    asterisks += "*";
                }
                sb.append(asterisks);
            } else {
                sb.append(word);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    @PutMapping("/updateComment/{id}")
    @ResponseBody
    public Comment updatecomment(@RequestBody Comment comment, Long idcomm) {
        return iService.updatecomment(comment,idcomm);
    }

    @DeleteMapping("/deleteComment/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") Long id) {
        iService.deleteComment(id);
    }

    @GetMapping("/getnombredecommentairepourunforum/{idforum}")
    public int getnbrecommentdansunforum(@PathVariable("idforum") Long forumid) {
        return iService.getnbrecommentdansunforum(forumid);
    }

    ///////////////////Forum////////////////////////////////
    ///{iduser}
    @PostMapping("/addforum/{iduser}")
    @ResponseBody
    public ResponseEntity<ForumPublication> addforumandassigntouser(@RequestBody ForumPublication forum, @PathVariable("iduser")Long iduser) throws IOException {
        Notification nf = new Notification();
       Actor user=userRepository.findById(iduser).orElse(null);

        if(containsbadword(forum.getObject())||containsbadword(forum.getTopic()) ) {
            nf.setCreatedAt(LocalDate.now());
            nf.setMessage("using bad word in the forum");
            nf.setUtilisateur(user);
            notificationRepository.save(nf);
            nf.setUtilisateur(forum.getUsersf());
           nf.setUtilisateur(user);

            return ResponseEntity.badRequest().build();


        }
        ForumPublication add=iService.addforumandassigntouser(forum,iduser);

        return ResponseEntity.ok(add);
    }
    @PutMapping("/updatereact/{id}")
    @ResponseBody
    public Reactions reactionupdate(@RequestBody Reactions r, @PathVariable("id")Integer id) {return iService.reactionupdate(r,id);}

    @PutMapping("/updateforum/{id}")
    @ResponseBody
    public ForumPublication updateforum(@RequestBody ForumPublication forum,@PathVariable("id")Long idforum) {
        return iService.updateforum(forum,idforum);
    }

    @DeleteMapping("/deleteforum/{id}")
    @ResponseBody
    public void deleteforum(@PathVariable Long id) {
        iService.deleteforum(id);
    }
    @DeleteMapping("/deletenotif")
    @ResponseBody
    public void deleteOldNotifications(){iService.deleteOldNotifications();}
    @DeleteMapping("/deletereaction/{id}")
    @ResponseBody
    public void deletereaction(@PathVariable Integer id) {
        iService.deletereaction(id);
    }

    @GetMapping("/getAllForums")
    public List<ForumPublication> getForumpub() {
        return iService.getForumpub();}

    @GetMapping("/rechercher/{mot}")

    public List<ForumPublication> rechercherParMot(@PathVariable("mot")String mot)
    {return iService.rechercherParMot(mot);}
    //////////////////////////////////Reactions///////////////////////////////////////////

    @GetMapping("/nbrelikes/{idforum}")
    public Long nbrelikes(@PathVariable Long idforum )
    {return iService.nbrelikes(idforum);}

    @GetMapping("/nbredislike/{idforum}")
    public Long nbredislikes(@PathVariable("idforum") Long idforum )
    {return iService.nbredislikes(idforum);}

    @PostMapping("/addreactiontoforum/{id}/{iduser}")
    @ResponseBody
    public Reactions ajouteretassocierunereactionaforum(@RequestBody Reactions r, @PathVariable("id")Long forum, @PathVariable("iduser")Long iduser)
    { return  iService.ajouteretassocierunereactionaforumetuser(r,forum,iduser);
    }
    ////////////////////////////////Badwords///////////////////////////////////////////////////////////////

    @GetMapping("/swearwords")
    public List<String> getSwearWords() throws IOException {
        return swearWordService.getSwearWords();
    }
    /////////////////////////////////////USER:RELATED TO FORUMMODULE////////////
    @GetMapping("/blockage/{id}")
    public String checkIfUserShouldBeBlocked(@PathVariable("id")Long userid) {

        return   iService.checkIfUserShouldBeBlocked(userid);
    }
    public boolean containsbadword(String content)throws IOException {
        List<String> badwords =swearWordService.getSwearWords();
        for(String word :badwords)
        {if (content.contains(word))
            return true;}
        return false;
    }
    @GetMapping("/badge/{id}")
    public String assignBadgeToUser(@PathVariable("id")Long user) {
        return iService.assignBadgeToUser(user);
    }
}
