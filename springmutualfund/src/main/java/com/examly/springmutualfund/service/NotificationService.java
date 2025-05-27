package com.examly.springmutualfund.service;

import java.util.List;

import com.examly.springmutualfund.model.Notification;

public interface NotificationService {

    Notification saveNotification(Notification notification);
    List<Notification> getAllNotifications();
    Notification getNotificationById(Long id);
    List<Notification> findByUserId(Long userId);
    void deleteNotification(Long id);

}
