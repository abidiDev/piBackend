package com.spring.pi.services;
/*
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;*/
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
@AllArgsConstructor
public class MailService {
/*

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Configuration configuration;
    public void  sendmail(Actor u, Map<String, String> Model) throws MessagingException, IOException, TemplateException {

         final String emailToRecipient = u.getEmail();
         final String emailSubject="Succesfuly";
         MimeMessage mimeMessage=javaMailSender.createMimeMessage();
         MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED);
         mimeMessageHelper.addAttachment("pdfreport.pdf",new ClassPathResource("C:/Users/USER/Desktop/pdf"));
        configuration.setDirectoryForTemplateLoading(new File(
                "src/main/resources/templates"));
        Template template=configuration.getTemplate("template");
        String html= FreeMarkerTemplateUtils.processTemplateIntoString(template,Model);
        mimeMessageHelper.setTo(emailToRecipient);
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(emailSubject);
        javaMailSender.send(mimeMessage);


    }*/
}
