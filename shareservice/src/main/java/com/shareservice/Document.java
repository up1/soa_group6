package com.shareservice;

import java.util.List;

/**
 * Created by melon on 4/24/2017.
 */
public class Document {

    private int id;
    private String tag;
    private String title;
    private String description;
    private long lastUpdated;
    private Department department;
    private boolean shared;

    private List<FileResponse> files;

    public Document() {
    }

    public Document(int id, String tag, String title, String description, long lastUpdated, Department department, boolean shared, List<FileResponse> files) {
        this.setId(id);
        this.setTag(tag);
        this.setTitle(title);
        this.setDescription(description);
        this.setLastUpdated(lastUpdated);
        this.setDepartment(department);
        this.setShared(shared);
        this.setFiles(files);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public List<FileResponse> getFiles() {
        return files;
    }

    public void setFiles(List<FileResponse> files) {
        this.files = files;
    }
}
