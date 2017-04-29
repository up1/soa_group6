package com.teamsmokeweed.model.dep;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class GetDepNameResponse {
    private int id;
    private String name;


    public GetDepNameResponse() {
    }

    public GetDepNameResponse(int id, String name) {
        this.id = id;
        this.name = name;
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
