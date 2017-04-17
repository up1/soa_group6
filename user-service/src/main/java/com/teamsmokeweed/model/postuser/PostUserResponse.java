package com.teamsmokeweed.model.postuser;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class PostUserResponse {
    String user_username, user_password, status;


    public PostUserResponse() {

    }

    public PostUserResponse(String status) {
        this.status = status;
    }

    public PostUserResponse(String user_username, String user_password, String status) {
        this.user_username = user_username;
        this.user_password = user_password;
        this.status = status;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
