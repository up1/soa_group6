package com.fileinfo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 6/3/2560.
 */
public class FileInfoRowMapper implements RowMapper<FileInfo> {
    @Override
    public FileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setDoc_id(rs.getInt("doc_id"));
        fileInfo.setFile_id(rs.getString("file_id"));
        fileInfo.setFile_date(rs.getString("file_date"));
        fileInfo.setRevision(rs.getString("revision"));
        fileInfo.setRaw_filename(rs.getString("raw_filename"));
        return fileInfo;
    }
}
