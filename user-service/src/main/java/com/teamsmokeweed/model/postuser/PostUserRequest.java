package com.teamsmokeweed.model.postuser;

import com.teamsmokeweed.model.dep.Department;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class PostUserRequest {
    String user_username, user_password, user_fname, user_lname;
    int dep_id, user_role, user_ispasswordchange;


    public PostUserRequest() {
    }

    public PostUserRequest(String user_username, String user_password, String user_fname, String user_lname, int dep_id, int user_role, int user_ispasswordchange) {
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.dep_id = dep_id;
        this.user_role = user_role;
        this.user_ispasswordchange = user_ispasswordchange;
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

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getUser_ispasswordchange() {
        return user_ispasswordchange;
    }

    public void setUser_ispasswordchange(int user_ispasswordchange) {
        this.user_ispasswordchange = user_ispasswordchange;
    }
}
