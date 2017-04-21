package com.teamsmokeweed.model.getdepname.getalldep;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetAllDepResponse {
    int id;
    String name;

    public GetAllDepResponse(int dep_id, String dep_name) {
        this.id = dep_id;
        this.name = dep_name;
    }

    public GetAllDepResponse() {
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
