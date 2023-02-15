package com.spring.pi.services;

import com.spring.pi.entities.notMapped.EmailDetails;

public interface ContactingService {
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
