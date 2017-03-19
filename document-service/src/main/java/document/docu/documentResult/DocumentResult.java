package document.docu.documentResult;

/**
 * Created by jongzazaal on 18/3/2560.
 */
public class DocumentResult {

    private int doc_id, user_id;
    private String doc_title, doc_desc, revision_date, doc_tag, user_fname, user_lname;


    public DocumentResult() {
    }

    public DocumentResult(int doc_id, int user_id, String doc_title, String doc_desc, String revision_date,
                          String doc_tag, String user_fname, String user_lname) {
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.doc_title = doc_title;
        this.doc_desc = doc_desc;
        this.revision_date = revision_date;
        this.doc_tag = doc_tag;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}