package com.teamsmokeweed.model.userinfo;

/**
 * Created by jongzazaal on 13/4/2560.
 */
public class UserInfoRequest {
    String username, password;

    public UserInfoRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfoRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
