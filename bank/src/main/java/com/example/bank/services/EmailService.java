package com.example.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс, реализующий логику отправки писем пользователю на электронную почту
 * @author Danil Kuzin
 */
@Service
@RequiredArgsConstructor
public class EmailService{
    private final JavaMailSender mailSender;

    /**
     * Поле, содержащее email отправителя
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Отправляет письмо на электронную почту
     * @param mailTo строка, хранящая адрес электронной почты получателя
     * @param subject тема письма
     * @param message содержание письма
     */
    public void send(String mailTo, String subject, String message) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(username);
            emailMessage.setTo(mailTo);
            emailMessage.setSubject(subject);
            emailMessage.setText(message);
            mailSender.send(emailMessage);
        });
    }
}