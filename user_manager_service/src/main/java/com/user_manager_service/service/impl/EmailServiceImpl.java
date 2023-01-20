package com.user_manager_service.service.impl;


import com.model.EmailDetailsV0;
import com.user_manager_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Async
    public String sendSimpleMail(EmailDetailsV0 emailDetailsV0) {
        String emailBody = "Dear " + emailDetailsV0.getRecipientFirstName() + ", \n\n" +
                "You are now officially a member of the gravitate team. Please use the following credentials to set up your account.\n\n" +
                "email: " +emailDetailsV0.getRecipient() + "\n"+
                "password: " + emailDetailsV0.getPassword();
        emailDetailsV0.setMsgBody(emailBody);
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetailsV0.getRecipient());
            mailMessage.setText(emailDetailsV0.getMsgBody());
            mailMessage.setSubject(emailDetailsV0.getSubject());
            javaMailSender.send(mailMessage);
            return "Send Mail Success";
        } catch (Exception e) {
            //log the exception
            return "Sending mail failed.";
        }
    }


    @Override
    public String sendMailWithAttachment(EmailDetailsV0 emailDetailsV0) {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetailsV0.getRecipient());
            mimeMessageHelper.setText(emailDetailsV0.getMsgBody());
            mimeMessageHelper.setSubject(
                    emailDetailsV0.getSubject());

            // Adding  attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(emailDetailsV0.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            System.out.println("sent");
            return "Mail sent Successfully";
        }
        catch (MessagingException e) {
            System.out.println("not sent");
            return "Error while sending mail!!!";
        }
    }
}
