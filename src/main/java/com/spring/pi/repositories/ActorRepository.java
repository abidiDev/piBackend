package com.spring.pi.repositories;

import com.spring.pi.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByUsername(String username);
    Optional<Actor> findById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
