package com.unnathy.fieldwise.userrouteassignment;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
