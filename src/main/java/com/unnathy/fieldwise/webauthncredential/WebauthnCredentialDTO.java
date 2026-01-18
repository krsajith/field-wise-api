package com.unnathy.fieldwise.webauthncredential;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebauthnCredentialDTO extends BaseDTO {

    private Long userId;
    private Long signatureCount;
    private String deviceName;
    private Instant lastUsedAt;
}
