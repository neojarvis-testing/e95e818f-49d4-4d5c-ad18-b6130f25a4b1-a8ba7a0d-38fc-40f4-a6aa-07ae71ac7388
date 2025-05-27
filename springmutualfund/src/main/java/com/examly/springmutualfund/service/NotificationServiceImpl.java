package com.examly.springmutualfund.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springmutualfund.model.Notification;
import com.examly.springmutualfund.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
   }

    @Override
    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
