package org.sunbong.allmart_fcm_api.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.sunbong.allmart_fcm_api.fcm.domain.UserDevice;
import org.sunbong.allmart_fcm_api.fcm.dto.DeviceRegistrationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.dto.NotificationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.dto.TopicNotificationRequestDTO;
import org.sunbong.allmart_fcm_api.fcm.repository.UserDeviceRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class FCMService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserDeviceRepository userDeviceRepository;

    public String sendNotification(NotificationRequestDTO request) {
        try {
            UserDevice userDevice = userDeviceRepository.findByUserIdAndMartId(request.getUserId(), request.getMartId())
                    .orElseThrow(() -> new RuntimeException("User device not found"));
            String response = sendNotificationToToken(userDevice.getFcmToken(), request.getTitle(), request.getBody());
            return "Notification sent successfully: " + response;
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send notification: ", e);
            return "Failed to send notification: " + e.getMessage();
        }
    }

    public String sendNotificationToTopic(TopicNotificationRequestDTO request) {
        try {
            String response = sendNotificationToTopic(request.getTopic(), request.getTitle(), request.getBody());
            return "Notification sent to topic successfully: " + response;
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send notification to topic: ", e);
            return "Failed to send notification to topic: " + e.getMessage();
        }
    }

    public String registerDevice(DeviceRegistrationRequestDTO request) {
        UserDevice userDevice = userDeviceRepository.findByUserIdAndMartId(request.getUserId(), request.getMartId())
                .orElse(new UserDevice());
        userDevice.setUserId(request.getUserId());
        userDevice.setMartId(request.getMartId());
        userDevice.setFcmToken(request.getFcmToken());
        userDeviceRepository.save(userDevice);
        return "Device registered successfully";
    }

    private String sendNotificationToToken(String token, String title, String body) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setToken(token)
                .build();

        return firebaseMessaging.send(message);
    }

    private String sendNotificationToTopic(String topic, String title, String body) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setTopic(topic)
                .build();

        return firebaseMessaging.send(message);
    }
}