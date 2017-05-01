package com.teamsmokeweed.model.putuser;

/**
 * Created by Sakan on 2 May 2017.
 */
public class PutSelfPasswordUpdateRequest {
    int id;
    String newPassword, oldPassword;

    public PutSelfPasswordUpdateRequest() {
    }

    public PutSelfPasswordUpdateRequest(int id, String newPassword, String oldPassword) {
        this.id = id;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
