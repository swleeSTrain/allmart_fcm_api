package org.sunbong.allmart_fcm_api.fcm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sunbong.allmart_fcm_api.fcm.dto.DeviceRegistrationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.dto.NotificationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.dto.TopicNotificationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.service.FCMService;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final FCMService fcmService;

    @PostMapping("/notifications/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO request) {
        String response = fcmService.sendNotification(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/notifications/send-topic")
    public ResponseEntity<String> sendNotificationToTopic(@RequestBody TopicNotificationRequestDTO request) {
        String response = fcmService.sendNotificationToTopic(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/devices/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceRegistrationRequestDTO request) {
        String response = fcmService.registerDevice(request);
        return ResponseEntity.ok(response);
    }
}