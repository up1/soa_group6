package com.inboxservice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 20/3/2560.
 */
public class InboxRowMapper implements RowMapper<Inbox>{
    @Override
    public Inbox mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Inbox inbox = new Inbox();

        inbox.setDoc_id(resultSet.getInt("doc_id"));
        inbox.setDoc_title(resultSet.getString("doc_title"));
        inbox.setDoc_desc(resultSet.getString("doc_desc"));
        inbox.setRevision_date(resultSet.getString("revision_date"));
        inbox.setDoc_tag(resultSet.getString("doc_tag"));
        inbox.setUser_fname(resultSet.getString("user_fname"));
        inbox.setUser_lname(resultSet.getString("user_lname"));
        inbox.setDep_name(resultSet.getString("dep_name"));

        String file_name = resultSet.getString("file_name");
        List<String> listFilename = new ArrayList<>();
        for(String filename:file_name.split(", ")){
            listFilename.add(filename);
        }
        inbox.setFile_name(listFilename);

        return inbox;
    }
}
