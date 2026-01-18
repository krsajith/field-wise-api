package com.unnathy.fieldwise.message;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
