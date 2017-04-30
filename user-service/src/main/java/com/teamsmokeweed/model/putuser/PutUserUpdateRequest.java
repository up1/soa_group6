package com.teamsmokeweed.model.putuser;

import com.teamsmokeweed.model.dep.Department;

/**
 * Created by Sakan on 30 Apr 2017.
 */
public class PutUserUpdateRequest {
    int id;
    String username, first_name, last_name;
    Department department;
    public PutUserUpdateRequest(){

    }

    public PutUserUpdateRequest(int id, String username, String first_name, String last_name, Department department) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.department = department;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
