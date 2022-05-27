package com.netcrecker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sermiazhko.e.a@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}