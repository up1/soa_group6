package com.teamsmokeweed.model.dep;

/**
 * Created by Sakan on 23 Apr 2017.
 */
public class Department {
    int id = 0;
    String name = "";

    public Department(){

    }
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Department(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
