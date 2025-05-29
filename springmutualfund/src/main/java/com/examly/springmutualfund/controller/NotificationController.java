package com.examly.springmutualfund.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springmutualfund.model.Notification;
import com.examly.springmutualfund.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('FUNDMANAGER') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
        Notification savedNotification = notificationService.saveNotification(notification);
        return ResponseEntity.status(201).body(savedNotification);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId){
        List<Notification> notifications = notificationService.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @PatchMapping("/{id}/mark-read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id){
        Notification updatedNotification = notificationService.markAsRead(id);
        return updatedNotification != null ? ResponseEntity.ok(updatedNotification) : ResponseEntity.notFound().build();
    }

    @PostMapping("/send-to-all")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> sendToAll(@RequestBody String message){
        notificationService.sendNotificationToAllUsers(message);
        return ResponseEntity.ok("Notification sent to all users");
    }
}
