package com.teamsmokeweed.model.getdepname.getdepname;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetDepNameResponse {
    private String dep_name;

    public GetDepNameResponse(String dep_name) {
        this.dep_name = dep_name;
    }

    public GetDepNameResponse() {
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
