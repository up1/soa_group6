package com.shareservice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 21/3/2560.
 */
public class ShareDocumentRowMapper implements RowMapper<ShareDocument>{

    @Override
    public ShareDocument mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        ShareDocument shareDocument = new ShareDocument();
        shareDocument.setDoc_id(resultSet.getInt("doc_id"));
        shareDocument.setDep_id(resultSet.getInt("dep_id"));

        return shareDocument;
    }
}
