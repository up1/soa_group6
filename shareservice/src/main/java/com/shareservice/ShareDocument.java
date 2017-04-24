package com.shareservice;

/**
 * Created by super on 21/3/2560.
 */
public class ShareDocument {
    private int documentId;
    private int departmentId;

    public ShareDocument() {
    }

    public ShareDocument(int documentId, int departmentId) {
        this.setDocumentId(documentId);
        this.setDepartmentId(departmentId);
    }


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}
