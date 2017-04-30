package com.teamsmokeweed.model.postuser;

import com.teamsmokeweed.model.dep.Department;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class PostUserRequest {
    String username, password, first_name, last_name;
    int dep_id, user_role, user_ispasswordchange;
    private Department department;


    public PostUserRequest() {
    }

    public PostUserRequest(String username, String password, String first_name, String last_name, Department department, int user_role, int user_ispasswordchange) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.department = department;
        this.user_role = user_role;
        this.user_ispasswordchange = user_ispasswordchange;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
