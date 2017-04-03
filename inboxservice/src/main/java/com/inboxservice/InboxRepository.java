package com.inboxservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by super on 20/3/2560.
 */

@Repository
public class InboxRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Inbox> getDepartmentInbox(int dep_id){

        List<Inbox> inbox = jdbcTemplate.query
                ("SELECT d.doc_id, d.doc_title, d.doc_desc, u.user_fname, u.user_lname , dep.dep_name, r.revision_date, d.doc_tag, GROUP_CONCAT(mfile.mfile_raw_filename SEPARATOR ', ') AS 'file_name' FROM docs d JOIN shares s ON (d.doc_id = s.doc_id) JOIN users u ON (d.user_id = u.user_id) JOIN dep dep ON (u.dep_id = dep.dep_id) JOIN (SELECT doc_id, MAX(revision_id) AS 'revision_id', MAX(revision_date) AS 'revision_date' FROM revision GROUP BY doc_id) r ON (d.doc_id = r.doc_id) JOIN mfile mfile ON (r.revision_id = mfile.revision_id) WHERE s.shares_id = ? GROUP BY d.doc_id"
                        , new Object[]{dep_id}, new InboxRowMapper());

        return inbox;
    }


}
