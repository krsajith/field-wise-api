package com.unnathy.fieldwise.pushnotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {
}
