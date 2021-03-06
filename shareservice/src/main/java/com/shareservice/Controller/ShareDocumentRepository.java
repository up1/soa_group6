package com.shareservice.Controller;

import com.shareservice.Adapter.DepAdapter;
import com.shareservice.Adapter.DocAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by super on 21/3/2560.
 */
@Repository
public class ShareDocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = false)
    public Map<String, Object> postShareToOtherDepartment(int documentId, int departmentId, String token) {
        //Declare map and get departmentname
        Map<String, Object> resource = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> departmentName = new DepAdapter().getDepartmentById(departmentId);

        try {
            //check documentId & departmentId
            if (!checkDepid(departmentId) && !checkDocid(documentId, token)) {
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department and document.");
                return result;
            }
            if (!checkDepid(departmentId)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department.");
                return result;
            }
            if (!checkDocid(documentId, token)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown document.");
                return result;
            }

            //Check owner of document
            Map<String, Object> ownerByDoc= new DocAdapter().getOwnerByDoc(documentId);
            if(departmentId == Integer.parseInt(ownerByDoc.get("id").toString())){
                //Return Result
                result.put("error", resource);
                resource.put("message", ownerByDoc.get("name") + " department is a owner of this document.");
                return result;
            }

            //INSERT SQL COMMAND
            String sql = "INSERT INTO shares (doc_id, dep_id) VALUES(?, ?)";
            this.jdbcTemplate.update(sql, documentId, departmentId);

            //Return result
            result.put("success", resource);
            resource.put("message", "This document has been shared to " + departmentName.get("name") +" department.");
            return result;

        } catch (Exception e) {
            //Return result
            result.put("error", resource);
            resource.put("message", "This document is already shared to " + departmentName.get("name") +" department.");
            return result;
        }
    }

    @Transactional(readOnly = false)
    public Map<String, Object> revokeDepartmentFromDocAndDep(int documentId, int departmentId, String token) {
        //Declare map and get departmentname
        Map<String, Object> resource = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> departmentName = new DepAdapter().getDepartmentById(departmentId);

        //check documentId & departmentId
        if (!checkDepid(departmentId) && !checkDocid(documentId, token)) {
            //Return result
            result.put("error", resource);
            resource.put("message", "Unknown department and document.");
            return result;
        }
        if (!checkDepid(departmentId)){
            //Return result
            result.put("error", resource);
            resource.put("message", "Unknown department.");
            return result;
        }
        if (!checkDocid(documentId, token)){
            //Return result
            result.put("error", resource);
            resource.put("message", "Unknown document.");
            return result;
        }

        //Check doc_id in shares table
        //Get list of data in shares table that before delete
        List<Map<String, Object>> listBefore = this.jdbcTemplate.queryForList("SELECT * FROM shares");
        int haveInDB = 0;

        for(Map<String, Object> row: listBefore){
            if(Integer.parseInt(row.get("doc_id").toString()) == documentId) {
                haveInDB = 1;
                break;
            }
        }
        if(haveInDB == 0){
            //Return Result
            result.put("error", resource);
            resource.put("message", "This document does not share to any department.");
            return result;
        }

        //Check owner of document
        Map<String, Object> ownerByDoc= new DocAdapter().getOwnerByDoc(documentId);
        if(departmentId == Integer.parseInt(ownerByDoc.get("id").toString())){
            //Return Result
            result.put("error", resource);
            resource.put("message", ownerByDoc.get("name") + " department is a owner of this document.");
            return result;
        }

//        //Check departmentId with documentId in shares table
//        List<Map<String, Object>> listDepartmentByDoc = this.jdbcTemplate.queryForList("SELECT * FROM shares WHERE doc_id = ?", new Object[]{documentId});
//        for(Map<String, Object> row: listDepartmentByDoc){
//            if(Integer.parseInt(row.get("dep_id").toString()) == departmentId){
//                break;
//            }
//            //Return Result
//
//            result.put("error", resource);
//            resource.put("message", "This document does not share to " + departmentName.get("name") +" department.");
//            return result;
//        }

        //Delete SQL command
        String deletesql = "DELETE FROM shares WHERE doc_id = ? AND dep_id = ?";
        this.jdbcTemplate.update(deletesql, documentId, departmentId);

        //Return result
        result.put("success", resource);
        resource.put("message", "This document has been revoked from " + departmentName.get("name") +" department.");
        return result;

    }

    @Transactional(readOnly = false)
    public Map<String, Object> revokeDepartmentFromDoc(int documentId, String token) {
        //Declare map and get departmentname
        Map<String, Object> resource = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        //Check documentId
        if (!checkDocid(documentId, token)){
            //Return result
            result.put("error", resource);
            resource.put("message", "Unknown document.");
            return result;
        }

        //Check doc_id in shares table
        //Get list of data in shares table that before delete
        List<Map<String, Object>> listBefore = this.jdbcTemplate.queryForList("SELECT * FROM shares");
        int haveInDB = 0;

        for(Map<String, Object> row: listBefore){
            if(Integer.parseInt(row.get("doc_id").toString()) == documentId) {
                haveInDB = 1;
                break;
            }
        }
        if(haveInDB == 0){
            //Return Result
            result.put("error", resource);
            resource.put("message", "This document does not share to any department.");
            return result;
        }

        //Delete SQL command
        String deletesql = "DELETE FROM shares WHERE doc_id = ?";
        this.jdbcTemplate.update(deletesql, documentId);

        //Return result
        result.put("success", resource);
        resource.put("message", "This document has been revoked from all departments.");
        return result;

    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getListDepartmentAllWithStatusExceptOwnerByDoc(int documentId) {

        List<Map<String, Object>> listDepartmentWithStatus = new ArrayList<>();
        List<Map<String, Object>> departmentList = new DepAdapter().getDepartmentAll();
        List<Map<String, Object>> listDepartmentByDoc = this.jdbcTemplate.queryForList("SELECT * FROM shares WHERE doc_id = ?", new Object[]{documentId});
        Map<String, Object> owner = new DocAdapter().getOwnerByDoc(documentId);

        for(Map<String, Object> dep: departmentList){
            int checkAddToList = 0;
            for(Map<String, Object> share : listDepartmentByDoc){
                if(Integer.parseInt(share.get("dep_id").toString()) == Integer.parseInt(dep.get("id").toString())){
                    Map<String, Object> departmentWithStatus = new HashMap<>();
                    departmentWithStatus.put("shared", true);
                    departmentWithStatus.put("id", dep.get("id"));
                    departmentWithStatus.put("name", dep.get("name"));
                    listDepartmentWithStatus.add(departmentWithStatus);
                    checkAddToList = 1;
                    break;
                }
            }
            if (checkAddToList == 0){
                if(Integer.parseInt(owner.get("id").toString()) != Integer.parseInt(dep.get("id").toString())){
                    Map<String, Object> departmentWithStatus = new HashMap<>();
                    departmentWithStatus.put("shared", false);
                    departmentWithStatus.put("id", dep.get("id"));
                    departmentWithStatus.put("name", dep.get("name"));
                    listDepartmentWithStatus.add(departmentWithStatus);
                }
            }
        }

        return listDepartmentWithStatus;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getListDepartmentSharingByDoc(int documentId){
        List<Map<String, Object>> departmentList = new DepAdapter().getDepartmentAll();
        List<Map<String, Object>> listDepartmentSharingByDoc = this.jdbcTemplate.queryForList("SELECT dep_id AS 'id' FROM shares WHERE doc_id = ?", new Object[]{documentId});
        List<Map<String, Object>> listDepartmentSharingWithDataByDoc = new ArrayList<>();

        for(Map<String, Object> dep: departmentList){
            for(Map<String, Object> share : listDepartmentSharingByDoc){
                if(Integer.parseInt(share.get("id").toString()) == Integer.parseInt(dep.get("id").toString())){
                    Map<String, Object> department = new HashMap<>();
                    department.put("id", dep.get("id"));
                    department.put("name", dep.get("name"));
                    listDepartmentSharingWithDataByDoc.add(department);
                }
            }
        }
        return  listDepartmentSharingWithDataByDoc;
    }


    @Transactional(readOnly = false)
    public Boolean checkDocid(int documentId, String token){
        List<Map<String, Object>> documentList = new DocAdapter().getDocumentAll(token);
        for(Map<String, Object> doc : documentList){
            if(Integer.parseInt(doc.get("id").toString()) == documentId){
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = false)
    public Boolean checkDepid(int departmentId){
        List<Map<String, Object>> departmentList = new DepAdapter().getDepartmentAll();
        for(Map<String, Object> dep : departmentList){
            if(Integer.parseInt(dep.get("id").toString())== departmentId){
                return true;
            }
        }
        return false;
    }

}
