package com.unnathy.fieldwise.dto;

import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {

    private String username;
    private String email;
    private String phone;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private Long roleId;
    private Long reportingManagerId;
    private String employeeCode;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String profilePhotoUrl;
    private Boolean isActive;
    private Boolean isVerified;
    private Instant lastLoginAt;
}
