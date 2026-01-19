package com.unnathy.fieldwise.errorreport;

public class ClientErrorReportRequest {
    public String timestamp;
    public String message;
    public String name;
    public String stack;
    public String route;
    public String url;
    public String userAgent;
    public User user;
    public Object context;

    public static class User {
        public Long userId;
        public String username;
        public String email;
    }
}