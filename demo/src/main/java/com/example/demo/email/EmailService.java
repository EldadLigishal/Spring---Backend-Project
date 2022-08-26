package com.example.demo.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    @Override
    @Async
    public void send(String toEmail, String fromEmail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(fromEmail, true);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject("Confirm your email");
            mimeMessageHelper.setFrom("no-reply@company.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException error) {
            LOGGER.error("Failed to send a email", error);
            throw new IllegalStateException("Failed to send a email");
        }
    }
}
