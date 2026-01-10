package com.taomish.web.common;

import com.taomish.common.domain.TaomishError;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Map;

@ControllerAdvice
public class ExceptionTranslator {

    public static final String REQUEST_ID = "requestId";

    @ExceptionHandler
    public ResponseEntity<Map<String,Object>> handleTaomishError(
            TaomishError ex,
            NativeWebRequest request
    ) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(getResponseHeaders())
                .body(Map.of("errorCode",ex.getErrorCode(),"errorMessage",ex.getErrorMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,Object>> handleTaomishError(
            java.lang.Exception ex,
            NativeWebRequest request
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(getResponseHeaders())
                .body(Map.of("errorCode",ex.getMessage()));
    }

    @NotNull
    private static HttpHeaders getResponseHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(REQUEST_ID,MDC.get(REQUEST_ID));
        return responseHeaders;
    }
}
