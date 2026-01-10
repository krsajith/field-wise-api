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
public class ComplaintDTO extends BaseDTO {

    private Long userId;
    private Long shopId;
    private String complaintType;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private Long assignedToId;
    private Instant resolvedAt;
    private String resolutionNotes;
    private String attachmentUrl;
}
