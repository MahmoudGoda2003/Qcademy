package com.example.backend.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MailSenderService {
    @Autowired
    private final JavaMailSender mailSender;
    private final String htmlBody;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.htmlBody = readHtmlFile();
    }


    private static String readHtmlFile() {
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get(".\\src\\main\\java\\com\\example\\backend\\services\\EmailTemplate.html"));
            return new String(encodedBytes);
        } catch (Exception e) {
            return "";
        }
    }

    private static String replacePlaceholders(String template, String userName, String otpCode) {
        template = template.replace("%DATE%", DateTimeFormatter.ofPattern("dd MMM, yyyy").format(LocalDate.now()));
        template = template.replace("%USERNAME%", userName);
        template = template.replace("%OTPCODE%", otpCode);
        return template;
    }

    @SneakyThrows
    public void sendNewMail(String to, String code) {
        String replacedHtmlBody = replacePlaceholders(htmlBody, to.substring(0, to.indexOf('@')), code);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        message.setHeader("No-Reply", "true");
        helper.setSubject("Validation code");
        helper.setText(replacedHtmlBody, true);

        mailSender.send(message);
    }
}
