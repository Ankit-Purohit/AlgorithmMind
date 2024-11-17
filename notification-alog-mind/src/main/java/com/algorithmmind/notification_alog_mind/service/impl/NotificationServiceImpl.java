package com.algorithmmind.notification_alog_mind.service.impl;


import com.algorithmmind.notification_alog_mind.dto.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Log4j2
public class NotificationServiceImpl {

    private final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${algo.mind.queue.name}", messageConverter = "messageConverter")
    public void receiveMessage(EmailDetails mail) {
        log.info("sending mail for user: {}", mail.getRecipient());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, !Objects.isNull(mail.getAttachment()));
            helper.setTo(mail.getRecipient());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody());
            if (mail.getAttachment() != null) {
                InputStreamSource inputStreamSource = new ByteArrayResource(mail.getAttachment());
                helper.addAttachment(mail.getAttachmentName(), inputStreamSource);
            }
            mailSender.send(message);
            log.info("message sent successfully for user: {}", mail.getRecipient());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
