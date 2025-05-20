package com.example.javafx;

public class Session {
    private static int loggedInUserId = -1;

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void clearSession() {
        loggedInUserId = -1;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != -1;
    }

}
