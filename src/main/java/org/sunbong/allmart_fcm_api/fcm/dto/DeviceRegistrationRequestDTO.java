package org.sunbong.allmart_fcm_api.fcm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRegistrationRequestDTO {
    private Long userId;
    private Long martId;
    private String fcmToken;
}
