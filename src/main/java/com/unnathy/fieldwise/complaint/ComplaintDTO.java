package com.unnathy.fieldwise.complaint;

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
