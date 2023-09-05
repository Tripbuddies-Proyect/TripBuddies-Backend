package com.upc.TRIPBUDDIES.service.impl;

import com.upc.TRIPBUDDIES.entities.Notification;
import com.upc.TRIPBUDDIES.repository.INotificationRespository;
import com.upc.TRIPBUDDIES.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl implements INotificationService {

    private final INotificationRespository notificationRepository;

    @Autowired
    public NotificationServiceImpl(INotificationRespository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public Notification save(Notification notification) throws Exception {
        return notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        notificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Notification> getAll() throws Exception {
        return notificationRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Notification> getById(Long id) throws Exception {
        return notificationRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Notification> findLastNotificationTraveller(long id) throws Exception {
        return notificationRepository.findLastNotificationTraveller(id);
    }

    @Override
    @Transactional
    public List<Notification> findLastNotificationBussiness(long id) throws Exception {
        return notificationRepository.findLastNotificationBussiness(id);
    }
}
