package com.shareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 21/3/2560.
 */
@Repository
public class ShareDocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = false)
    public ServiceStatus postShareToOtherDepartment(int doc_id, int dep_id) {
        try {
            String sql = "INSERT INTO shares_service.shares (doc_id, dep_id) VALUES(?, ?)";
            this.jdbcTemplate.update(sql, doc_id, dep_id);
            return new ServiceStatus(true, "Add Department To Document Complete");
        } catch (Exception e) {
            return new ServiceStatus(false, "Add Department To Document Fail");
        }
    }

    @Transactional(readOnly = false)
    public ServiceStatus revokeDepartmentFromDoc(int doc_id, int dep_id) {
        try {
            List<ShareDocument> listBefore = this.jdbcTemplate.query("SELECT doc_id, dep_id FROM shares_service.shares", new ShareDocumentRowMapper());

            String deletesql = "DELETE FROM shares_service.shares WHERE doc_id = ? AND dep_id = ?";
            this.jdbcTemplate.update(deletesql, doc_id, dep_id);

            List<ShareDocument> listAfter = this.jdbcTemplate.query("SELECT doc_id, dep_id FROM shares_service.shares", new ShareDocumentRowMapper());

            if (listAfter.size() == listBefore.size()) {
                return new ServiceStatus(false, "Revoke Department From Document Fail");
            } else {
                return new ServiceStatus(true, "Revoke Department From Document Complete");
            }

        } catch (Exception e) {
            return new ServiceStatus(false, "Revoke Department From Document Fail");
        }
    }

    @Transactional(readOnly = true)
    public List<Department> getListShareDepartmentByDoc(int doc_id) {
        List<Department> listdep;
        String sql = "SELECT dep.dep_id, dep.dep_name\n" +
                "FROM\n" +
                "(SELECT s.dep_id\n" +
                "FROM shares_service.shares s\n" +
                "JOIN document_service.documents d\n" +
                "USING (doc_id)\n" +
                "JOIN users_service.users u\n" +
                "USING (user_id)\n" +
                "WHERE s.doc_id = ?) tab\n" +
                "JOIN department_service.department dep\n" +
                "ON (tab.dep_id = dep.dep_id)";
        listdep = this.jdbcTemplate.query(sql, new Object[]{doc_id}, new DepartmentRowMapper());

        return listdep;
    }

}
