package com.spring.pi.controllers;
// Importing required classes

import com.spring.pi.entities.notMapped.EmailDetails;
import com.spring.pi.services.ContactingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/contact")
public class ContatctingController {
    @Autowired private ContactingService contactingService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details)
    {
        String status
                = contactingService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = contactingService.sendMailWithAttachment(details);

        return status;
    }
}