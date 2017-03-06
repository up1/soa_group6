package com.fileinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by super on 6/3/2560.
 */
@Repository
public class FileInfoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public FileInfo findById(Integer id){
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM Files WHERE file_id=?", new Object[]{id}, new FileInfoRowMapper());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
