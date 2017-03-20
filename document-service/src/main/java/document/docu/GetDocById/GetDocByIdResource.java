package document.docu.GetDocById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jongzazaal on 20/3/2560.
 */
public class GetDocByIdResource {
    String doc_tittle, doc_date, doc_tag, doc_desc;
    int user_id, doc_id;
    List<Integer> shares_id = new ArrayList<>();
    List<String> dep_name = new ArrayList<>();

    public GetDocByIdResource(int doc_id, String doc_tittle,String doc_desc, String doc_date, String doc_tag, int user_id, List<Integer> shares_id, List<String> dep_name) {
        this.doc_id = doc_id;
        this.doc_tittle = doc_tittle;
        this.doc_date = doc_date;
        this.doc_tag = doc_tag;
        this.user_id = user_id;
        this.shares_id = shares_id;
        this.dep_name = dep_name;
        this.doc_desc = doc_desc;
    }

    public String getDoc_desc() {
        return doc_desc;
    }

    public void setDoc_desc(String doc_desc) {
        this.doc_desc = doc_desc;
    }

    public GetDocByIdResource() {
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_tittle() {
        return doc_tittle;
    }

    public void setDoc_tittle(String doc_tittle) {
        this.doc_tittle = doc_tittle;
    }

    public String getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(String doc_date) {
        this.doc_date = doc_date;
    }

    public String getDoc_tag() {
        return doc_tag;
    }

    public void setDoc_tag(String doc_tag) {
        this.doc_tag = doc_tag;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Integer> getShares_id() {
        return shares_id;
    }

    public void setShares_id(List<Integer> shares_id) {
        this.shares_id = shares_id;
    }

    public List<String> getDep_name() {
        return dep_name;
    }

    public void setDep_name(List<String> dep_name) {
        this.dep_name = dep_name;
    }
}