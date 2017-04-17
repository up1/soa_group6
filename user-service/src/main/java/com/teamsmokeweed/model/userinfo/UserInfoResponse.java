package com.teamsmokeweed.model.userinfo;

/**
 * Created by jongzazaal on 13/4/2560.
 */
public class UserInfoResponse {
    int user_id, dep_id, user_role, user_ispasswordchange;
    String user_username, user_fname, user_lname, dep_name;

    public UserInfoResponse() {
    }

    public UserInfoResponse(int user_id, int dep_id, int user_role, int user_ispasswordchange, String user_username, String user_fname, String user_lname, String dep_name) {
        this.user_id = user_id;
        this.dep_id = dep_id;
        this.user_role = user_role;
        this.user_ispasswordchange = user_ispasswordchange;
        this.user_username = user_username;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.dep_name = dep_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
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

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
