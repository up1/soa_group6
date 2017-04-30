package com.teamsmokeweed.model.putuser;

/**
 * Created by Sakan on 30 Apr 2017.
 *
 */
public class PutSelfUserUpdateRequest {
    int userID;
    String username, password;
    public PutSelfUserUpdateRequest(){}

    public PutSelfUserUpdateRequest(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
