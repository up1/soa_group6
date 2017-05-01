package com.teamsmokeweed.model.putuser;

/**
 * Created by Sakan on 2 May 2017.
 */
public class PutSelfPasswordUpdateRequest {
    int id;
    String password;
    public PutSelfPasswordUpdateRequest(){}

    public PutSelfPasswordUpdateRequest(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
