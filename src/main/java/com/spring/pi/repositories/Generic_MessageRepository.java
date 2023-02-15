package com.spring.pi.repositories;

import com.spring.pi.entities.Generic_Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Generic_MessageRepository extends JpaRepository<Generic_Message, Long> {
}