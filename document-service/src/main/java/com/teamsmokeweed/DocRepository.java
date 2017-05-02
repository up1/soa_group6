package com.teamsmokeweed;

import com.teamsmokeweed.model.getalldoc.dep.DepAdapter;
import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import com.teamsmokeweed.model.getalldoc.files.FilesAdapter;
import com.teamsmokeweed.model.getalldoc.share.ShareAdapter;
import com.teamsmokeweed.model.getalldoc.upload.UploadAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 15/4/2560.
 */
@Repository
public class DocRepository {
    private DepAdapter depAdapter;
    private ShareAdapter shareAdapter;
    private FilesAdapter filesAdapter;

    public DocRepository() {
        this.depAdapter = new DepAdapter();
        this.shareAdapter = new ShareAdapter();
        this.filesAdapter = new FilesAdapter();

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getDocument(int key, int user_id, String order, String orderBy, String token){
//        0->recent เห็นของเรากับของคนที่แชร์ให้เรา
//        1->message เห็นของคนที่แชร์ให้เรา ไม่เห็นของเรา
        int myDepartment = 0;

        if(user_id>0) {
            myDepartment = (Integer) this.depAdapter.getDepepartment(user_id).get("id");
        }

//        document all
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList("SELECT doc_id AS id, doc_tag AS tag, doc_title AS title, doc_desc AS description, doc_date AS lastUpdated, user_id FROM documents ORDER BY ? ?",
                new Object[]{orderBy, order});
        List<Map<String, Object>> result2 = new ArrayList<>();

        if (order.equals("ASC")){
            for (int i = 0; i <result.size();i++) {
                result2 = checkShare(result, result2, key,myDepartment,i, user_id, token);
            }
        }
        else {
            for (int i = result.size()-1; i >=0;i--) {
                result2 = checkShare(result, result2, key,myDepartment,i, user_id, token);
            }
        }

        return result2;
    }

    private List<Map<String, Object>> checkShare(List< Map<String, Object>> result, List< Map<String, Object>> result2,
                                                 int key, int myDepartment, int i, int user_id, String token){
//        0->recent เห็นของเรากับของคนที่แชร์ให้เรา
//        1->message เห็นของคนที่แชร์ให้เรา ไม่เห็นของเรา
        Map<String, Object> r = result.get(i);
        List<Map<String, Object>> share = this.shareAdapter.getShare((Integer) r.get("id"), token);

//        department ของคนที่สร้างdocนี้
        int otherDepartment = (Integer) this.depAdapter.getDepepartment((Integer) r.get("user_id")).get("id");

        //ทั้งหมด
        if(user_id==0){
            return addToList(r,result2);
        }
        if(key == 2){
            if (myDepartment==otherDepartment){
                r.put("shared", true);
                return addToList(r, result2);
            }
            return result2;
        }

//        recent และ doc ที่เราและคนสร้างอยู่ในdepartmentเดียวกัน
        if(key==0 && myDepartment==otherDepartment){
            r.put("shared", true);
            return addToList(r,result2);
        }

//        doc ที่แชร์ใช้departmentเรา
        if(this.shareAdapter.isShare(share, myDepartment)){
            r.put("shared", false);
            return addToList(r,result2);
        }
        return result2;

    }

    public List<Map<String, Object>> addToList(Map<String, Object> r, List<Map<String, Object>> result2){
        r.put("department",this.depAdapter.getDepepartment((Integer) r.get("user_id")));
        try {
            r.put("files", this.filesAdapter.getFileInfo((Integer)r.get("id")));
        }
        catch (Exception e){
            Map<String, Object> resource = new HashMap<>();
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Service is not available now");
            resource.put("errror", error);
            r.put("files", resource);
        }
        r.remove("user_id");
        result2.add(r);
        return result2;
    }

    public Map<String, Object> createDocument(String title, String des, String tag, int user_id, MultipartFile[] multipartFiles){

        Map<String, Object> resource = null;
        try {
            this.jdbcTemplate.update("INSERT INTO documents(doc_title, doc_desc, user_id, doc_tag) VALUES (?, ?, ?, ?)",
                    new Object[]{title, des, user_id, tag});


            Map<String, Object> m = this.jdbcTemplate.queryForMap("SELECT doc_id FROM documents WHERE doc_title = ? AND doc_desc = ? AND user_id = ? AND doc_tag = ?",
                    new Object[]{title, des, user_id, tag});



            for (MultipartFile mfile : multipartFiles) {

                UploadAdapter uploadAdapter = new UploadAdapter();
                uploadAdapter.Upload(mfile, (Integer) m.get("doc_id"));
            }

            resource = setResource(true, "Sample Document has been created");

        }
        catch (Exception e){

             resource = setResource(false, "Sample Document cannot be created");
        }
        return resource;

    }

    public Map<String, Object> getDocumentByIdDoc(int doc_id){
        Map<String, Object> map = this.jdbcTemplate.queryForMap("SELECT doc_id AS id, doc_tag AS tag, doc_title AS title, doc_desc AS description, doc_date AS lastUpdated, user_id FROM documents WHERE doc_id = ?",
                new Object[]{doc_id});

        DepAdapter depAdapter = new DepAdapter();
        try {
            GetDepNameResponse response = depAdapter.getDepName((Integer) map.get("user_id"));
            map.put("department", response);
        }catch (Exception e){
            Map<String, Object> resource = setResource(false, "Service is not available now");
            map.put("departments", resource);
        }

        map.remove("user_id");

        FilesAdapter filesAdapter = new FilesAdapter();
        try {
            List<Map<String, Object>> fileResponse = filesAdapter.getFileInfo((Integer) map.get("id"));
            map.put("files", fileResponse);
        }catch (Exception e){
            Map<String, Object> resource = setResource(false, "Service is not available now");
            map.put("files", resource);
        }

        return map;
    }

    public Map<String, Object> updateDocument(int doc_id, String title, String des, String tag, MultipartFile[] multipartFiles){

        try {
            this.jdbcTemplate.update("UPDATE documents SET doc_title = ?, doc_desc = ?, doc_date= now(), doc_tag = ? WHERE doc_id = ?",
                    new Object[]{title, des, tag, doc_id});

            for (MultipartFile mfile : multipartFiles) {

                UploadAdapter uploadAdapter = new UploadAdapter();
                uploadAdapter.Upload(mfile, doc_id);
            }
            return setResource(true, "Document has been Updated");
        }catch (Exception e){
            return setResource(false, "Document cannot be updated");
        }
    }

    public GetDepNameResponse ownerDepartment(int doc_id){
        Map<String, Object> map = this.jdbcTemplate.queryForMap("SELECT user_id FROM documents WHERE doc_id = ?", new Object[]{doc_id});
        DepAdapter depAdapter = new DepAdapter();
        GetDepNameResponse response = depAdapter.getDepName((Integer) map.get("user_id"));
        return response;
    }
    public Map<String, Object> deleteDocument(int id_doc){

        try {
            this.jdbcTemplate.update("DELETE FROM documents WHERE doc_id = ?", new Object[]{id_doc});
            return setResource(true, "Document has been deleted");
        }
        catch (Exception e){
            return setResource(false, "Document cannot be deleted");
        }


    }
    public Map<String, Object> setResource(boolean result, String message){
        Map<String, Object> resource = new HashMap<>();

        if(result){
            Map<String, Object> success = new HashMap<>();
            success.put("message", message);
            resource.put("success", success);
        }
        else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", message);
            resource.put("errror", error);
        }
        return resource;
    }

}
