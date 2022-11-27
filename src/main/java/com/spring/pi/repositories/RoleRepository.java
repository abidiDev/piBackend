package com.spring.pi.repositories;

import java.util.Optional;

import com.spring.pi.entities.ERole;
import com.spring.pi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
