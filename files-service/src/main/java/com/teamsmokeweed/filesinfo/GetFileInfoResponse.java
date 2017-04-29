package com.teamsmokeweed.filesinfo;

/**
 * Created by jongzazaal on 24/4/2560.
 */
public class GetFileInfoResponse {
    String name;
    long time, size;
    int revision;


    public GetFileInfoResponse() {
    }

    public GetFileInfoResponse(String name, long size, long time, int revision) {
        this.name = name;
        this.size = size;
        this.time = time;
        this.revision = revision;
    }

    public GetFileInfoResponse(String name, long size, int revision) {
        this.name = name;
        this.size = size;
        this.revision = revision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
