package com.unnathy.fieldwise.pushnotification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.Instant;
import java.util.Map;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.time.Instant;
import java.util.Map;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.time.Instant;
import java.util.Map;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "push_notifications")
public class PushNotification extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false, length = Integer.MAX_VALUE)
    private String body;

    @Column(name = "notification_type", length = 50)
    private String notificationType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data")
    private Map<String, Object> data;

    @ColumnDefault("false")
    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "sent_at")
    private Instant sentAt;

}
