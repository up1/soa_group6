package com.shareservice;

import java.util.List;

/**
 * Created by super on 21/3/2560.
 */
public class ShareDocument {
    private int doc_id;
    private int shares_id;

    public ShareDocument() {
    }

    public ShareDocument(int doc_id, int shares_id) {
        this.setDoc_id(doc_id);
        this.setShares_id(shares_id);
    }


    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getShares_id() {
        return shares_id;
    }

    public void setShares_id(int shares_id) {
        this.shares_id = shares_id;
    }

}
