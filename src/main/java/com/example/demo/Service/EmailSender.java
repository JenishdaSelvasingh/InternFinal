package com.example.demo.Service;

import com.example.demo.Exception.ApplicationException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseEntity<String> sendEmail(String email, String subject, String content) {
        try {
            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("kingsmansees@gmail.com", "Test Admin");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true);
            this.mailSender.send(message);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (UnsupportedEncodingException | MessagingException var6) {
            Exception e = var6;
            throw new ApplicationException("MAIL_SEND_FAILED", "Failed to send email: " + ((Exception)e).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
