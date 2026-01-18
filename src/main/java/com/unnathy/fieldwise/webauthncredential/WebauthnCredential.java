package com.unnathy.fieldwise.webauthncredential;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "webauthn_credentials")
public class WebauthnCredential extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "credential_id", nullable = false)
    private byte[] credentialId;

    @Column(name = "public_key", nullable = false)
    private byte[] publicKey;

    @ColumnDefault("0")
    @Column(name = "signature_count")
    private Long signatureCount;

    @Column(name = "device_name")
    private String deviceName;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_used_at")
    private Instant lastUsedAt;

}
