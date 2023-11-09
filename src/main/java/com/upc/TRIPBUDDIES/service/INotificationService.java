package com.upc.TRIPBUDDIES.service;

import com.upc.TRIPBUDDIES.entities.Notification;

import java.util.List;

public interface INotificationService extends CrudService<Notification>{
    List<Notification> findLastNotificationTraveller(long id) throws Exception;
    List<Notification> findLastNotificationBussiness(long id) throws Exception;

}
