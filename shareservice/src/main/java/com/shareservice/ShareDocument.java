package com.shareservice;

import java.util.List;

/**
 * Created by super on 21/3/2560.
 */
public class ShareDocument {
    private int doc_id;
    private int dep_id;

    public ShareDocument() {
    }

    public ShareDocument(int doc_id, int dep_id) {
        this.setDoc_id(doc_id);
        this.setDep_id(dep_id);
    }


    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

}
