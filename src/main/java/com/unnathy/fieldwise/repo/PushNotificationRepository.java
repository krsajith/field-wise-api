package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {
}
