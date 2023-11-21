package com.example.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private final JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNewMail(String to, String OTP) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        String subject = "Qcademy SignUp";
        message.setSubject(subject);
        String body1 = """
                Welcome to Qcademy, this email is sent for validation, you don't need to reply
                This email address tried to sign up in Qcademy, if you do recognise this attempt, please enter the following number in the box that appeared to you to validate the email

                """;
        String body2 = "\n\nOr you can press the link below\n\n";
        String body3 = """
                If you don't recognise this action, please ignore this email.

                Thanks for your time
                Qcademy team.
                """;
        message.setText(body1 + OTP + body2 + body3);
        mailSender.send(message);
    }
}
