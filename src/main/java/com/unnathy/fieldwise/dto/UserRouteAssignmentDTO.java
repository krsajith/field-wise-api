package com.unnathy.fieldwise.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRouteAssignmentDTO extends BaseDTO {

    private Long userId;
    private Long routeId;
    private LocalDate assignedDate;
    private Boolean isActive;
}
