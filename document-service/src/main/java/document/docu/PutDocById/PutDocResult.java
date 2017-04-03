package document.docu.PutDocById;

/**
 * Created by jongzazaal on 21/3/2560.
 */
public class PutDocResult {
    int doc_id;
    String doc_title, doc_desc, dog_tag;

    public PutDocResult() {
    }

    public PutDocResult(int doc_id, String doc_title, String doc_desc, String dog_tag) {
        this.doc_id = doc_id;
        this.doc_title = doc_title;
        this.doc_desc = doc_desc;
        this.dog_tag = dog_tag;
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

    public String getDog_tag() {
        return dog_tag;
    }

    public void setDog_tag(String dog_tag) {
        this.dog_tag = dog_tag;
    }
}
