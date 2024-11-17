package com.algorithmmind.notification_alog_mind.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDetails {

    private String subject;
    private String recipient;
    private String body;
    private byte[] attachment;
    private String attachmentName;

}
