package com.shareservice;

/**
 * Created by melon on 4/24/2017.
 */
public class FileResponse {

    private String name;
    private long time;
    private long size;
    private int revision;

    public FileResponse() {
    }

    public FileResponse(String name, long time, long size, int revision) {
        this.name = name;
        this.time = time;
        this.size = size;
        this.revision = revision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
