package com.teamsmokeweed.filesinfo;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jongzazaal on 24/4/2560.
 */
public class GetFileInfoRowMapper implements RowMapper<GetFileInfoResponse>{
    @Override
    public GetFileInfoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        String strList = rs.getString("file_name");
        List<String> listFileName;
        String fileName;
        try {
            listFileName = new ArrayList<String>(Arrays.asList(strList.split("\\|")));
            fileName = listFileName.get(listFileName.size()-1);
        }
        catch (Exception e){
            fileName = strList;
        }

        GetFileInfoResponse response = new GetFileInfoResponse(fileName,
                rs.getLong("file_size"), rs.getInt("file_upload_revision"));
        String time = rs.getString("file_upload_date");


        try {
            response.setTime(StringTodate(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return response;
    }

    public long StringTodate(String ori) throws ParseException {

//        2017-03-01 17:05:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(ori);
        return date.getTime();
    }
}
