package com.unnathy.fieldwise.security;

import com.unnathy.fieldwise.core.LocationContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LocationFilter extends OncePerRequestFilter {

    private static final String HEADER_LATITUDE = "X-Latitude";
    private static final String HEADER_LONGITUDE = "X-Longitude";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            BigDecimal latitude = parseDecimal(request.getHeader(HEADER_LATITUDE));
            BigDecimal longitude = parseDecimal(request.getHeader(HEADER_LONGITUDE));
            if (latitude != null || longitude != null) {
                LocationContext.set(latitude, longitude);
            }
            filterChain.doFilter(request, response);
        } finally {
            LocationContext.clear();
        }
    }

    private BigDecimal parseDecimal(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
