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
    public ServiceStatus postShareToOtherDepartment(int documentId, int departmentId) {
        try {
            //check documentId & departmentId
            if (!checkDepid(departmentId) && !checkDocid(documentId)) {
                return new ServiceStatus("error", "Unknown department and document.");
            }
            if (!checkDepid(departmentId)){
                return new ServiceStatus("error", "Unknown department.");
            }
            if (!checkDocid(documentId)){
                return new ServiceStatus("error", "Unknown document.");
            }

            String sql = "INSERT INTO shares_service.shares (doc_id, dep_id) VALUES(?, ?)";
            this.jdbcTemplate.update(sql, documentId, departmentId);
            Department departmentName = new DepAdapter().getDepartmentById(departmentId);

            return new ServiceStatus("success", "Document has been shared to "+ departmentName.getName() + " department.");
        } catch (Exception e) {
            Department departmentName = new DepAdapter().getDepartmentById(departmentId);
            return new ServiceStatus("error", "Document cannot be shared to "+ departmentName.getName() + " department.");

        }
    }

    @Transactional(readOnly = false)
    public ServiceStatus revokeDepartmentFromDoc(int documentId, int departmentId) {
        try {

            //check documentId & departmentId
            if (!checkDepid(departmentId) && !checkDocid(documentId)) {
                return new ServiceStatus("error", "Unknown department and document.");
            }
            if (!checkDepid(departmentId)){
                return new ServiceStatus("error", "Unknown department.");
            }
            if (!checkDocid(documentId)){
                return new ServiceStatus("error", "Unknown document.");
            }

            List<ShareDocument> listBefore = this.jdbcTemplate.query("SELECT * FROM shares_service.shares", new ShareDocumentRowMapper());

            String deletesql = "DELETE FROM shares_service.shares WHERE doc_id = ? AND dep_id = ?";
            this.jdbcTemplate.update(deletesql, documentId, departmentId);

            List<ShareDocument> listAfter = this.jdbcTemplate.query("SELECT * FROM shares_service.shares", new ShareDocumentRowMapper());

            Department departmentName = new DepAdapter().getDepartmentById(departmentId);

            if (listAfter.size() == listBefore.size()) {
                return new ServiceStatus("error", "Document cannot be revoked from " + departmentName.getName() + " department.");
            } else {
                return new ServiceStatus("success", "Document has been revoked from " + departmentName.getName() + " department.");
            }

        } catch (Exception e) {
            return new ServiceStatus("error", "Unknown department and document.");
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
