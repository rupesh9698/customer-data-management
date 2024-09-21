package com.dm.customer.dto;

/**
 * User details class used in login response
 */
public class UserDetails {

    private String userId;
    private String userName;
    private String userEmail;
    private String password;

    public UserDetails() {
    }

    public UserDetails(String userId, String userName, String userEmail, String password) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
