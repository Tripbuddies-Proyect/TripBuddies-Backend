package com.upc.TRIPBUDDIES.repository;

import com.upc.TRIPBUDDIES.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface INotificationRespository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT DISTINCT ON (lastNotification.emitter_id)* FROM (SELECT * FROM notifications n WHERE receiver_id =:userId ORDER BY id DESC) AS lastNotification", nativeQuery = true)
    List<Notification> findLastNotificationTraveller(@Param("userId") long userId);

    @Query(value = "SELECT DISTINCT ON (lastNotification.receiver_id)* FROM (SELECT * FROM notifications n WHERE emitter_id =:userId ORDER BY id DESC) AS lastNotification;", nativeQuery = true)
    List<Notification> findLastNotificationBussiness(@Param("userId") long userId);
}
