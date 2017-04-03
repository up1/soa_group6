package com.inboxservice;

import java.util.List;

/**
 * Created by super on 20/3/2560.
 */
public class Inbox {

    private int doc_id;
    private String doc_title;
    private String doc_desc;
    private String revision_date;
    private String doc_tag;
    private String user_fname;
    private String user_lname;
    private String dep_name;
    private List<String> file_name;

    public Inbox() {
    }

    public Inbox(int doc_id, String doc_title, String doc_desc, String revision_date, String doc_tag, String user_fname, String user_lname, String dep_name, List<String> file_name) {
        this.setDoc_id(doc_id);
        this.setDoc_title(doc_title);
        this.setDoc_desc(doc_desc);
        this.setRevision_date(revision_date);
        this.setDoc_tag(doc_tag);
        this.setUser_fname(user_fname);
        this.setUser_lname(user_lname);
        this.setDep_name(dep_name);
        this.setFile_name(file_name);
    }


    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }


    public String getDoc_title() {
        return doc_title;
    }

    public void setDoc_title(String doc_title) {
        this.doc_title = doc_title;
    }

    public String getDoc_desc() {
        return doc_desc;
    }

    public void setDoc_desc(String doc_desc) {
        this.doc_desc = doc_desc;
    }

    public String getRevision_date() {
        return revision_date;
    }

    public void setRevision_date(String revision_date) {
        this.revision_date = revision_date;
    }

    public String getDoc_tag() {
        return doc_tag;
    }

    public void setDoc_tag(String doc_tag) {
        this.doc_tag = doc_tag;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public List<String> getFile_name() {
        return file_name;
    }

    public void setFile_name(List<String> file_name) {
        this.file_name = file_name;
    }
}
