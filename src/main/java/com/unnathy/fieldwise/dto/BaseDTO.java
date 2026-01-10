package com.unnathy.fieldwise.dto;

import lombok.Data;

import java.time.Instant;

@Data
public abstract class BaseDTO {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
