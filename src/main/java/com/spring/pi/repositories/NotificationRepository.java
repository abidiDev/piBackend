package com.spring.pi.repositories;

import com.spring.pi.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUtilisateur_Id(Long id);
    void deleteByCreatedAtBefore(LocalDate threLocalDate);
}
