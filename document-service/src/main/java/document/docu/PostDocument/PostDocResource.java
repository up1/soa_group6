package document.docu.PostDocument;

/**
 * Created by jongzazaal on 19/3/2560.
 */
public class PostDocResource {
    private String doc_title, doc_desc, doc_tag;
    private int doc_id, user_id;

    public PostDocResource() {
    }

    public PostDocResource(String doc_title, String doc_desc, String doc_tag, int doc_id, int user_id) {
        this.doc_title = doc_title;
        this.doc_desc = doc_desc;
        this.doc_tag = doc_tag;
        this.doc_id = doc_id;
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

    public String getDoc_tag() {
        return doc_tag;
    }

    public void setDoc_tag(String doc_tag) {
        this.doc_tag = doc_tag;
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
}
