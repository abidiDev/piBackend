package com.spring.pi.repositories;

import com.spring.pi.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    public Actor findActorByEmail(String email);

    public Actor findActorByResetPasswordToken(String token);

}
