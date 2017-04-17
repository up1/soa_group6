package com.teamsmokeweed.model.getdepname.getalldep;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetAllDepResponse {
    int dep_id;
    String dep_name;

    public GetAllDepResponse(int dep_id, String dep_name) {
        this.dep_id = dep_id;
        this.dep_name = dep_name;
    }

    public GetAllDepResponse() {
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
