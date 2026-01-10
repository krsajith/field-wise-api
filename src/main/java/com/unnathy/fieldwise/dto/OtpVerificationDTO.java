package com.unnathy.fieldwise.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerificationDTO extends BaseDTO {

    private String phone;
    private String otpCode;
    private String purpose;
    private Boolean isVerified;
    private Instant expiresAt;
}
