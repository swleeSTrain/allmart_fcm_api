package org.sunbong.allmart_fcm_api.fcm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicNotificationRequestDTO {
    private String topic;
    private String title;
    private String body;
    // getters and setters

}
