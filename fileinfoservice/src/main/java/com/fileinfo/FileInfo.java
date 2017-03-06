package com.fileinfo;

/**
 * Created by super on 6/3/2560.
 */
public class FileInfo {
    private Integer doc_id;
    private String file_id;
    private String file_date;
    private String revision;
    private String raw_filename;

    public Integer getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Integer doc_id) {
        this.doc_id = doc_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_date() {
        return file_date;
    }

    public void setFile_date(String file_date) {
        this.file_date = file_date;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getRaw_filename() {
        return raw_filename;
    }

    public void setRaw_filename(String raw_filename) {
        this.raw_filename = raw_filename;
    }
}
