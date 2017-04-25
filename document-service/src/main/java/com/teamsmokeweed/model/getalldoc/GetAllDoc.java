package com.teamsmokeweed.model.getalldoc;

import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import com.teamsmokeweed.model.getalldoc.files.FileResponse;

import java.util.List;

/**
 * Created by jongzazaal on 16/4/2560.
 */
public class GetAllDoc{


    int id;
    String tag, title, description;
    long lastUpdated;
    GetDepNameResponse department;
    boolean shared;

    List<FileResponse> files;

    public GetAllDoc(int id, String tag,  String title, String description, long lastUpdated,
                     GetDepNameResponse department, boolean shared, List<FileResponse> files) {
        this.id = id;
        this.tag = tag;
        this.title = title;
        this.description = description;
        this.lastUpdated = lastUpdated;
        this.department = department;
        this.shared = shared;
        this.files = files;
    }


    public GetAllDoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public GetDepNameResponse getDepartment() {
        return department;
    }

    public void setDepartment(GetDepNameResponse department) {
        this.department = department;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<FileResponse> getFiles() {
        return files;
    }

    public void setFiles(List<FileResponse> files) {
        this.files = files;
    }
}
