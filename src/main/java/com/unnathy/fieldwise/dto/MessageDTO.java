package com.unnathy.fieldwise.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO extends BaseDTO {

    private Long fromUserId;
    private Long toUserId;
    private String messageType;
    private String subject;
    private String body;
    private Boolean isRead;
    private Instant readAt;
}
