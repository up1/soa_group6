package com.teamsmokeweed.model.putuser;

/**
 * Created by Sakan on 30 Apr 2017.
 *
 */
public class PutSelfUserUpdateRequest {
    int id;
    String username, password;
    public PutSelfUserUpdateRequest(){}

    public PutSelfUserUpdateRequest(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
