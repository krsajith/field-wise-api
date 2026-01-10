package com.unnathy.fieldwise.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO extends BaseDTO {

    private Long userId;
    private Long shopId;
    private String activityType;
    private String title;
    private String description;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;
    private String location;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String status;
    private String outcome;
}
