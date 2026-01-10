package com.unnathy.fieldwise.common.constants;

import java.util.regex.Pattern;

public final class HttpClientConstants {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_URL_ENCODED = "application/x-www-form-urlencoded";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REQUEST_ID = "X-Request-Id";
    public static final Pattern PATTERN_QUERY_PARAM = Pattern.compile("\\{[^}]+}");

    private HttpClientConstants() {
    }
}
