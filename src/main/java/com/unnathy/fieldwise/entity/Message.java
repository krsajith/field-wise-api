package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "from_user_id")
    private Long fromUser;

    @Column(name = "to_user_id")
    private Long toUser;

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
