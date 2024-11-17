package com.algorithmmind.notification_alog_mind.check;

import com.algorithmmind.notification_alog_mind.dto.EmailDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Check {

    @Value("${algo.mind.exchange.name}")
    private String mailExchange;

    @Value("${algo.mind.queue.name}")
    private String mailQueue;

    @Value("${algo.mind.routing.key}")
    private String mailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public Check(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Handles the logic to send an email notification when the application is fully initialized.
     *
     * <p>This method is annotated with {@code @EventListener(ApplicationReadyEvent.class)},
     * ensuring it is executed after the Spring Boot application context has fully started.
     * It prepares an {@code EmailDetails} object with a recipient, subject, body, and an
     * attachment loaded from the classpath, and then sends it via RabbitMQ.</p>
     *
     * @throws IOException if an error occurs while reading the attachment file from the classpath.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void push() throws IOException {
        ClassPathResource resource = new ClassPathResource("resource.txt");
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient("legion@yopmail.com")
                .subject("Testing")
                .body("test")
                .attachment(resource.getContentAsByteArray())
                .attachmentName("resource.txt")
                .build();
        rabbitTemplate.convertAndSend(mailExchange, mailRoutingKey, emailDetails);
    }

}
