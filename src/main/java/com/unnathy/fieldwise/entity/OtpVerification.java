package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "otp_verifications")
public class OtpVerification extends BaseEntity {

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "otp_code", nullable = false, length = 10)
    private String otpCode;

    @Column(name = "purpose", nullable = false, length = 50)
    private String purpose;

    @ColumnDefault("false")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

}