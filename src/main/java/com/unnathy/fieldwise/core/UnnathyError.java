package com.unnathy.fieldwise.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class UnnathyError extends Throwable {
    private final String errorCode;
    private final String errorMessage;
    private final Throwable throwable;
}
