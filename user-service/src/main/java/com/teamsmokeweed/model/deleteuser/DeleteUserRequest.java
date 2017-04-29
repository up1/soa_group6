package com.teamsmokeweed.model.deleteuser;

/**
 * Created by Sakan on 30 Apr 2017.
 */
public class DeleteUserRequest {
    int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public DeleteUserRequest(int userID) {
        this.userID = userID;
    }

    public DeleteUserRequest() {
    }
}
