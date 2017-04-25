package com.teamsmokeweed;

import com.teamsmokeweed.model.getalldoc.GetAllDoc;
import com.teamsmokeweed.model.getalldoc.GetAllDocRowMapper;
import com.teamsmokeweed.model.getalldoc.dep.DepAdapter;
import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import com.teamsmokeweed.model.getalldoc.files.FileResponse;
import com.teamsmokeweed.model.getalldoc.files.FilesAdapter;
import com.teamsmokeweed.model.getalldoc.upload.UploadAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 15/4/2560.
 */
@Repository
public class DocRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GetAllDoc> GetAllDoc(){

        List<GetAllDoc> getAllDocs = this.jdbcTemplate.query("SELECT doc_id, doc_title, doc_desc, doc_date, doc_tag, user_id FROM documents", new GetAllDocRowMapper());


        return getAllDocs;
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
            List<FileResponse> fileResponse = filesAdapter.GetFileInfo((Integer) map.get("id"));
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

    public Map<String, Object> UpdateDocument(int doc_id, Map<String, Object> obj){
        Map<String, Object> resource = new HashMap<>();
        try {
            this.jdbcTemplate.update("UPDATE documents SET doc_title = ?, doc_desc = ?, doc_date= now(), doc_tag = ? WHERE doc_id = ?",
                    new Object[]{(String)obj.get("title"), (String) obj.get("description"), (String) obj.get("tag"), doc_id});
            Map<String, Object> success = new HashMap<>();
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
