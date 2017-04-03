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
    public ServiceStatus postShareToOtherDepartment(int doc_id, int shares_id) {
        try {
            String sql = "INSERT INTO shares (doc_id, shares_id) VALUES(?, ?)";
            this.jdbcTemplate.update(sql, doc_id, shares_id);
            return new ServiceStatus(true, "Add Department To Document Complete");
        } catch (Exception e) {
            return new ServiceStatus(false, "Add Department To Document Fail");
        }
    }

    @Transactional(readOnly = false)
    public ServiceStatus revokeDepartmentFromDoc(int doc_id, int shares_id) {
        try {
            List<ShareDocument> listbefore = this.jdbcTemplate.query("SELECT doc_id, shares_id FROM shares", new ShareDocumentRowMapper());

            String deletesql = "DELETE FROM shares WHERE doc_id = ? AND shares_id = ?";
            this.jdbcTemplate.update(deletesql, doc_id, shares_id);

            List<ShareDocument> listAfter = this.jdbcTemplate.query("SELECT doc_id, shares_id FROM shares", new ShareDocumentRowMapper());

            if (listAfter.equals(listbefore)) {
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
        String sql = "SELECT f.dep_id, f.dep_name\n" +
                "FROM\n" +
                "(SELECT s.shares_id\n" +
                "FROM shares s\n" +
                "JOIN docs d\n" +
                "USING (doc_id)\n" +
                "JOIN users u\n" +
                "USING (user_id)\n" +
                "WHERE s.doc_id = ?) tab\n" +
                "JOIN dep f\n" +
                "ON (tab.shares_id = f.dep_id)";
        listdep = this.jdbcTemplate.query(sql, new Object[]{doc_id}, new DepartmentRowMapper());

        return listdep;
    }

    @Transactional(readOnly = true)
    public List<Department> getListDepartmentExceptMe(int dep_id){
        List<Department> listdep;
        String sql = "SELECT * FROM dep WHERE NOT dep_id = ?";
        listdep = this.jdbcTemplate.query(sql, new Object[]{dep_id}, new DepartmentRowMapper());

        return listdep;
    }

    @Transactional(readOnly = true)
    public List<Department> getListDepartmentAll(){
        List<Department> listdep;
        String sql = "SELECT * FROM dep";
        listdep = this.jdbcTemplate.query(sql, new Object[]{}, new DepartmentRowMapper());

        return listdep;
    }
}
