package com.spring.pi.repositories;

import com.spring.pi.entities.ForumPublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumPublicationRepository extends JpaRepository<ForumPublication,Long> {
    List<ForumPublication>findByUsersfId(Long id);

   List<ForumPublication> findByObjectContainingIgnoreCaseOrTopicContainingIgnoreCaseOrCommentsContentContainingIgnoreCase(String mot, String mot2, String mot3);

}
