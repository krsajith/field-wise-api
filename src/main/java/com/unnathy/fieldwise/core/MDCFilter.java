package com.taomish.web.common;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@Slf4j
public class MDCFilter implements Filter {

    public static final String REQUEST_ID = "requestId";
    public static final String RESPONSE_TIMESTAMP = "responseTimestamp";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
                httpServletResponse
        );
        var uniqueId = ((HttpServletRequest)servletRequest).getHeader(REQUEST_ID);
        if(uniqueId==null){
            uniqueId = UUID.randomUUID().toString();
        }
        MDC.put(REQUEST_ID, uniqueId);
        filterChain.doFilter(servletRequest, responseWrapper);
        responseWrapper.setHeader(REQUEST_ID, uniqueId);
        responseWrapper.setHeader(RESPONSE_TIMESTAMP, Instant.now().toString());
        responseWrapper.copyBodyToResponse();
    }
}
