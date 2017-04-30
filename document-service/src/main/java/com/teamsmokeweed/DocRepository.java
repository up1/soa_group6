package com.teamsmokeweed;

import com.teamsmokeweed.model.getalldoc.GetAllDoc;
import com.teamsmokeweed.model.getalldoc.GetAllDocRowMapper;
import com.teamsmokeweed.model.getalldoc.dep.DepAdapter;
import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import com.teamsmokeweed.model.getalldoc.files.FilesAdapter;
import com.teamsmokeweed.model.getalldoc.share.ShareAdapter;
import com.teamsmokeweed.model.getalldoc.upload.UploadAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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

    public List<GetAllDoc> GetAllDoc(){

        List<GetAllDoc> getAllDocs = this.jdbcTemplate.query("SELECT doc_id, doc_title, doc_desc, doc_date, doc_tag, user_id FROM documents", new GetAllDocRowMapper());


        return getAllDocs;
    }

    public List<Map<String, Object>> GetDocument(int key, int user_id, String order, String orderBy, String token){
//        0->recent เห็นของเรากับของคนที่แชร์ให้เรา
//        1->message เห็นของคนที่แชร์ให้เรา ไม่เห็นของเรา
        int myDepartment = 0;

        if(user_id>0) {
            myDepartment = (Integer) this.depAdapter.getDepepartment(user_id).get("id");
        }

        List<Map<String, Object>> result = this.jdbcTemplate.queryForList("SELECT doc_id AS id, doc_tag AS tag, doc_title AS title, doc_desc AS description, doc_date AS lastUpdated, user_id FROM documents ORDER BY ? ?",
                new Object[]{orderBy, order});
        List<Map<String, Object>> result2 = new ArrayList<>();
//        System.out.println("MyDepartment: "+myDepartment);
//        System.out.println(result);


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

//        System.out.println(result);
//        System.out.println(result2);
        return result2;
    }

    private List<Map<String, Object>> checkShare(List< Map<String, Object>> result, List< Map<String, Object>> result2,
                                                 int key, int myDepartment, int i, int user_id, String token){
//        0->recent เห็นของเรากับของคนที่แชร์ให้เรา
//        1->message เห็นของคนที่แชร์ให้เรา ไม่เห็นของเรา
        Map<String, Object> r = result.get(i);
        List<Map<String, Object>> share = this.shareAdapter.getShare((Integer) r.get("id"), token);

        int otherDepartment = (Integer) this.depAdapter.getDepepartment((Integer) r.get("user_id")).get("id");
        if(user_id==0){
//            r.put("department",this.depAdapter.getDepepartment((Integer) r.get("user_id")).get("id")));
//            r.put("files", this.filesAdapter.GetFileInfo((Integer)r.get("id")));
//            r.remove("user_id");
//            result2.add(r);
            return AddToList(r,result2);
        }

        if(key==0 && myDepartment==otherDepartment){
//            r.put("department",this.depAdapter.getDepepartment(user_id));
//            r.put("files", this.filesAdapter.GetFileInfo((Integer)r.get("id")));
//            r.remove("user_id");
//            result2.add(r);
            r.put("shared", true);
            return AddToList(r,result2);
        }
        if(this.shareAdapter.isShare(share, myDepartment)){
//                System.out.println(r);
//            r.put("department",this.depAdapter.getDepepartment(user_id));
//            r.put("files", this.filesAdapter.GetFileInfo((Integer)r.get("id")));
//            r.remove("user_id");
//            result2.add(r);
            r.put("shared", false);
            return AddToList(r,result2);
        }
        return result2;

    }

    public List<Map<String, Object>> AddToList(Map<String, Object> r, List<Map<String, Object>> result2){
        r.put("department",this.depAdapter.getDepepartment((Integer) r.get("user_id")).get("id"));
        r.put("files", this.filesAdapter.GetFileInfo((Integer)r.get("id")));
        r.remove("user_id");
        result2.add(r);
        return result2;
    }

    public Map<String, Object> CreateDocument(Map<String, Object> obj){
//        List<Map<String, Object>> map = this.jdbcTemplate.queryForList("SELECT doc_id, doc_title, doc_desc FROM documents");
//        Map<String, Object> resource = new HashMap<>();

        Map<String, Object> resource = new HashMap<>();
        try {
            this.jdbcTemplate.update("INSERT INTO documents(doc_title, doc_desc, user_id, doc_tag) VALUES (?, ?, ?, ?)",
                    new Object[]{obj.get("title"), obj.get("description"), obj.get("user_id"), obj.get("tag")});

            Map<String, Object> success = new HashMap<>();
            success.put("message", "Sample Document has been created");
            resource.put("success", success);

        }
        catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Sample Document cannot be created");
            resource.put("errror", error);
        }
        return resource;
    }

    public Map<String, Object> CreateDocument(String title, String des, String tag, int user_id, MultipartFile[] multipartFiles){

        Map<String, Object> resource = new HashMap<>();
        try {
            this.jdbcTemplate.update("INSERT INTO documents(doc_title, doc_desc, user_id, doc_tag) VALUES (?, ?, ?, ?)",
                    new Object[]{title, des, user_id, tag});


            Map<String, Object> m = this.jdbcTemplate.queryForMap("SELECT doc_id FROM documents WHERE doc_title = ? AND doc_desc = ? AND user_id = ? AND doc_tag = ?",
                    new Object[]{title, des, user_id, tag});

            Map<String, Object> success = new HashMap<>();

            for (MultipartFile mfile : multipartFiles) {

                UploadAdapter uploadAdapter = new UploadAdapter();
                uploadAdapter.Upload(mfile, (Integer) m.get("doc_id"));
            }

            success.put("message", "Sample Document has been created");
            resource.put("success", success);

        }
        catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Sample Document cannot be created");
            resource.put("errror", error);
        }
//        return resource;

        return resource;

    }

    public Map<String, Object> GetDocumentByIdDoc (int doc_id){
        Map<String, Object> map = this.jdbcTemplate.queryForMap("SELECT doc_id AS id, doc_tag AS tag, doc_title AS title, doc_desc AS description, doc_date AS lastUpdated, user_id FROM documents WHERE doc_id = ?",
                new Object[]{doc_id});

        DepAdapter depAdapter = new DepAdapter();
        try {
            GetDepNameResponse response = depAdapter.GetDepName((Integer) map.get("user_id"));
            map.put("department", response);
        }catch (Exception e){
            Map<String, Object> resource = new HashMap<>();
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Service is not available now");
            resource.put("errror", error);
            map.put("departments", resource);
        }

        map.remove("user_id");

        FilesAdapter filesAdapter = new FilesAdapter();
        try {
            List<Map<String, Object>> fileResponse = filesAdapter.GetFileInfo((Integer) map.get("id"));
            map.put("files", fileResponse);
        }catch (Exception e){
//            map.put("files", "Service Files is crash");
            Map<String, Object> resource = new HashMap<>();
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Service is not available now");
            resource.put("errror", error);
            map.put("files", resource);
        }

        return map;
    }

    public Map<String, Object> UpdateDocument(int doc_id, String title, String des, String tag, MultipartFile[] multipartFiles){
        Map<String, Object> resource = new HashMap<>();
        try {
            this.jdbcTemplate.update("UPDATE documents SET doc_title = ?, doc_desc = ?, doc_date= now(), doc_tag = ? WHERE doc_id = ?",
                    new Object[]{title, des, tag, doc_id});
            Map<String, Object> success = new HashMap<>();
            for (MultipartFile mfile : multipartFiles) {

                UploadAdapter uploadAdapter = new UploadAdapter();
                uploadAdapter.Upload(mfile, doc_id);
            }
            success.put("message", "Document has been Updated");
            resource.put("success", success);
        }catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Document cannot be updated");
            resource.put("errror", error);
        }
        return resource;
    }

    public GetDepNameResponse OwnerDepartment(int doc_id){
        Map<String, Object> map = this.jdbcTemplate.queryForMap("SELECT user_id FROM documents WHERE doc_id = ?", new Object[]{doc_id});
        DepAdapter depAdapter = new DepAdapter();
        GetDepNameResponse response = depAdapter.GetDepName((Integer) map.get("user_id"));
        return response;
    }

}
