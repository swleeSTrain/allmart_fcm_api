package org.sunbong.allmart_fcm_api.fcm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDTO {
    private Long userId;
    private Long martId;
    private String title;
    private String body;
}
