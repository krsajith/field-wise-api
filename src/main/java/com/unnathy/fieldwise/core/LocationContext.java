package com.unnathy.fieldwise.core;

import java.math.BigDecimal;



public final class LocationContext {

    private static final ThreadLocal<BigDecimal> LATITUDE = new ThreadLocal<>();
    private static final ThreadLocal<BigDecimal> LONGITUDE = new ThreadLocal<>();

    private LocationContext() {
    }

    public static void set(BigDecimal latitude, BigDecimal longitude) {
        LATITUDE.set(latitude);
        LONGITUDE.set(longitude);
    }

    public static BigDecimal getLatitude() {
        return LATITUDE.get();
    }

    public static BigDecimal getLongitude() {
        return LONGITUDE.get();
    }

    public static void clear() {
        LATITUDE.remove();
        LONGITUDE.remove();
    }
}
