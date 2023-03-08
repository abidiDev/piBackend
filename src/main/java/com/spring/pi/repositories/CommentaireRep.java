package com.spring.pi.repositories;

import com.spring.pi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentaireRep extends JpaRepository<Comment,Long> {
    //List<Comment>findByForumsIdforum(Long forumid);
  //  @Query("select (r.content) from Comment r ")
    //String getcontent();
    List<Comment> findByUserId(Long id);

}
