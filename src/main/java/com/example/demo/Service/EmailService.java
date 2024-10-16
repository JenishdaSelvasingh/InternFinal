package com.example.demo.Service;

import java.util.Random;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());
    @Autowired
    private JavaMailSender mailSender;

    public EmailService() {
    }

    public void sendOtpEmail(String to, String otp, int expirationMinutes) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp + ". It will expire in " + expirationMinutes + " minute(s).");

        try {
            this.mailSender.send(message);
            LOGGER.info("Email sent successfully to " + to);
        } catch (Exception var6) {
            Exception e = var6;
            LOGGER.severe("Failed to send email to " + to + ": " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
