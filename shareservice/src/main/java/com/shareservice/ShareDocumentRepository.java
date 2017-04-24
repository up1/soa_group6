package com.shareservice;

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
    public Map<String, Object> postShareToOtherDepartment(int documentId, int departmentId) {
        //Declare map and get departmentname
        Map<String, Object> resource = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Department departmentName = new DepAdapter().getDepartmentById(departmentId);

        try {
            //check documentId & departmentId
            if (!checkDepid(departmentId) && !checkDocid(documentId)) {
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department or document.");
                return result;
            }
            if (!checkDepid(departmentId)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department.");
                return result;
            }
            if (!checkDocid(documentId)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown document.");
                return result;
            }

            //INSERT SQL COMMAND
            String sql = "INSERT INTO shares_service.shares (doc_id, dep_id) VALUES(?, ?)";
            this.jdbcTemplate.update(sql, documentId, departmentId);

            //Return result
            result.put("success", resource);
            resource.put("message", "Document has been shared to " + departmentName.getName() +" department.");
            return result;

        } catch (Exception e) {
            //Return result
            result.put("error", resource);
            resource.put("message", "Document cannot be shared to " + departmentName.getName() +" department.");
            return result;
        }
    }

    @Transactional(readOnly = false)
    public Map<String, Object> revokeDepartmentFromDoc(int documentId, int departmentId) {
        //Declare map and get departmentname
        Map<String, Object> resource = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Department departmentName = new DepAdapter().getDepartmentById(departmentId);

        try {
            //check documentId & departmentId
            if (!checkDepid(departmentId) && !checkDocid(documentId)) {
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department or document.");
                return result;
            }
            if (!checkDepid(departmentId)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown department.");
                return result;
            }
            if (!checkDocid(documentId)){
                //Return result
                result.put("error", resource);
                resource.put("message", "Unknown document.");
                return result;
            }
            //Get list of data in shares table that before delete
            List<Map<String, Object>> listBefore = this.jdbcTemplate.queryForList("SELECT * FROM shares_service.shares");

            //Delete SQL command
            String deletesql = "DELETE FROM shares_service.shares WHERE doc_id = ? AND dep_id = ?";
            this.jdbcTemplate.update(deletesql, documentId, departmentId);

            //Get list of data in shares table that after delete
            List<Map<String, Object>> listAfter = this.jdbcTemplate.queryForList("SELECT * FROM shares_service.shares");

            //check delete command was work;
            if (listAfter.size() == listBefore.size()) {
                //Return result
                result.put("error", resource);
                resource.put("message", "Document cannot be revoked from " + departmentName.getName() +" department.");
                return result;

            } else {
                //Return result
                result.put("success", resource);
                resource.put("message", "Document has been revoked from " + departmentName.getName() +" department.");
                return result;
            }

        } catch (Exception e) {
            //Return result
            result.put("error", resource);
            resource.put("message", "Document cannot be revoked from " + departmentName.getName() +" department.");
            return result;
        }
    }

    @Transactional(readOnly = true)
    public List<DepartmentStatus> getListDepartmentWithStatusByDoc(int documentId) {

        List<DepartmentStatus> listDepartmentWithStatus = new ArrayList<>();
        List<Department> departmentList = new DepAdapter().getDepartmentAll();
        List<ShareDocument> listDepartmentByDoc = this.jdbcTemplate.query("SELECT * FROM shares_service.shares WHERE doc_id = ?", new Object[]{documentId}, new ShareDocumentRowMapper());

        for(Department dep: departmentList){
            int checkAddToList = 0;
            for(ShareDocument share : listDepartmentByDoc){
                if(share.getDepartmentId() == dep.getId()){
                    listDepartmentWithStatus.add(new DepartmentStatus(dep, true));
                    checkAddToList = 1;
                    break;
                }
            }
            if (checkAddToList == 0){
                listDepartmentWithStatus.add(new DepartmentStatus(dep, false));
            }
        }


        return listDepartmentWithStatus;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getShare(){
        List<Map<String, Object>> listShare;
        System.out.println("hello");
        listShare = this.jdbcTemplate.queryForList("SELECT * FROM shares_service.shares");
        return listShare;
    }

    @Transactional(readOnly = false)
    public Boolean checkDocid(int documentId){
        List<Document> documentList = new DocAdapter().getDocumentAll();
        for(Document doc : documentList){
            if(doc.getId() == documentId){
                return true;
            }
        }
        return false;
    }


    @Transactional(readOnly = false)
    public Boolean checkDepid(int departmentId){
        List<Department> departmentList = new DepAdapter().getDepartmentAll();
        for(Department dep : departmentList){
            if(dep.getId() == departmentId){
                return true;
            }
        }
        return false;
    }

}
