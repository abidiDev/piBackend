package com.spring.pi.repositories;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.ForumPublication;
import com.spring.pi.entities.Reactions;
import com.spring.pi.entities.TypeReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactionRepository  extends JpaRepository<Reactions,Integer> {
    @Query("select count (r)from Reactions r where r.typeReaction=:type AND r.forumpublication.idforum=:idforum")
    Long countReactionssurunforum(@Param("type") TypeReaction typeReaction, @Param("idforum")Long idforum);


    List<Reactions> findByUserId(Long id);

    Reactions findByUserAndForumpublication(Actor user, ForumPublication forumPublication);
}
