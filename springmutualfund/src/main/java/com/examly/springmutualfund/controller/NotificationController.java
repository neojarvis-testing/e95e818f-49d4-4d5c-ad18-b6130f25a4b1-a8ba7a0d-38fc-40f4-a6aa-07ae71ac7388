package com.examly.springmutualfund.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springmutualfund.model.Notification;
import com.examly.springmutualfund.service.NotificationService;

@RestController
public class NotificationController {

    private NotificationService notificationService;

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId){
        List<Notification> notifications = notificationService.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}
