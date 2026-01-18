package com.unnathy.fieldwise.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
