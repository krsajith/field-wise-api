package com.unnathy.fieldwise.message;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @ColumnDefault("'DIRECT'")
    @Column(name = "message_type", length = 50)
    private String messageType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body", nullable = false, length = Integer.MAX_VALUE)
    private String body;

    @ColumnDefault("false")
    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "read_at")
    private Instant readAt;

}
