package com.codeofraj.zoomverse.model;

public class Session {

    private boolean isOnline;
    private String lastActive;

    // Constructors
    public Session() {
    }

    // Getters and Setters
    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }

    @Override
    public String toString() {
        return "Session{" +
                "isOnline='" + isOnline + '\'' +
                ", lastActive='" + lastActive + '\'' +
                "}";
    }
}
