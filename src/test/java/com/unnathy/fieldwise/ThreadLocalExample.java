package com.unnathy.fieldwise;

public class ThreadLocalExample {

    // ThreadLocal variable to store user context per thread
    private static ThreadLocal<UserContext> userContextHolder = new ThreadLocal<>();

    // Simple UserContext class
    static class UserContext {
        private String userId;
        private String sessionId;

        public UserContext(String userId, String sessionId) {
            this.userId = userId;
            this.sessionId = sessionId;
        }

        public String getUserId() {
            return userId;
        }

        public String getSessionId() {
            return sessionId;
        }

        @Override
        public String toString() {
            return "UserContext{userId='" + userId + "', sessionId='" + sessionId + "'}";
        }
    }

    // Service class that uses ThreadLocal
    static class UserService {
        public static void setUserContext(String userId, String sessionId) {
            UserContext context = new UserContext(userId, sessionId);
            userContextHolder.set(context);
            System.out.println(Thread.currentThread().getName() + " - Context set: " + context);
        }

        public static UserContext  getUserContext() {
            return userContextHolder.get();
        }

        public static void clearUserContext() {
            userContextHolder.remove();
            System.out.println(Thread.currentThread().getName() + " - Context cleared");
        }

        public void processRequest() {
            UserContext context = getUserContext();
            System.out.println(Thread.currentThread().getName() + " - Processing request for: " + context);

            // Simulate some work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserService service = new UserService();

        // Create multiple threads
        Thread thread1 = new Thread(() -> {
            try {
                UserService.setUserContext("user-123", "session-abc");
                service.processRequest();
                service.processRequest(); // Access context again
            } finally {
                UserService.clearUserContext(); // Always clean up
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            try {
                UserService.setUserContext("user-456", "session-xyz");
                service.processRequest();
                service.processRequest();
            } finally {
                UserService.clearUserContext();
            }
        }, "Thread-2");

        Thread thread3 = new Thread(() -> {
            try {
                UserService.setUserContext("user-789", "session-pqr");
                service.processRequest();
                service.processRequest();
            } finally {
                UserService.clearUserContext();
            }
        }, "Thread-3");

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to complete
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("\nAll threads completed. Each maintained its own context.");
    }
}