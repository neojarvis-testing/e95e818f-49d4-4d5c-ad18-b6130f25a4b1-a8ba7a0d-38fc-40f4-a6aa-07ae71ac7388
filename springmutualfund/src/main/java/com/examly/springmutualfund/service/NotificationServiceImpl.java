package com.examly.springmutualfund.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springmutualfund.model.Notification;
import com.examly.springmutualfund.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

    @Override
    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if(notification != null){
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }

    @Override
    public void sendNotificationToAllUsers(String message) {
        List<Long> userIds = userService.getAllUserIds();
        for(Long userId:userIds){
            Notification notification = new Notification();
            notification.setUserId(userId);
            notification.setMessage(message);
            notification.setDateSent(LocalDateTime.now());
            notification.setRead(false);
            notificationRepository.save(notification);
        } 

    }

    
}
