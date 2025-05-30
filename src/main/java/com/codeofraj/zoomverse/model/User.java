package com.codeofraj.zoomverse.model;

import java.util.List;
import java.util.UUID;

public class User {

    private String userId;
    private String email;
    private String password;
    private String username;
    private List<String> sharedDZIs;
    private Session session;

    public User() {
        this.userId = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getSharedDZIs() {
        return sharedDZIs;
    }

    public void setSharedDZIs(List<String> sharedDZIs) {
        this.sharedDZIs = sharedDZIs;
    }

    public Session getSession() { return session; }

    public void setSession(Session session) { this.session = session; }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sharedDzi=" + sharedDZIs + '\'' +
                ", session=" + session +
                '}';
    }
}