package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO extends BaseDTO {

    private Long userId;
    private Long shopId;
    private String feedbackType;
    private String subject;
    private String description;
    private Integer rating;
    private String status;
    private String attachmentUrl;
}
