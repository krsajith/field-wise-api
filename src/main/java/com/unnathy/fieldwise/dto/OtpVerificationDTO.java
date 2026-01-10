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
public class OtpVerificationDTO extends BaseDTO {

    private String phone;
    private String otpCode;
    private String purpose;
    private Boolean isVerified;
    private Instant expiresAt;
}
