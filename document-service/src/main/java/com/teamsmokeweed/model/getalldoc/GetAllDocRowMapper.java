package com.teamsmokeweed.model.getalldoc;

import com.teamsmokeweed.model.getalldoc.dep.DepAdapter;
import com.teamsmokeweed.model.getalldoc.files.FilesAdapter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jongzazaal on 17/4/2560.
 */
public class GetAllDocRowMapper implements RowMapper<GetAllDoc>{

    @Override
    public GetAllDoc mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepAdapter depAdapter = new DepAdapter();
        FilesAdapter filesAdapter = new FilesAdapter();
        GetAllDoc getAllDoc = null;
        try {
            getAllDoc = new GetAllDoc(rs.getInt("doc_id"), rs.getString("doc_tag"),
                    rs.getString("doc_title"),
                    rs.getString("doc_desc"), stringToDate(rs.getString("doc_date")),
                    depAdapter.getDepName(rs.getInt("user_id")), true, filesAdapter.getFileInfo(rs.getInt("doc_id")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getAllDoc;

    }
    public long stringToDate(String ori) throws ParseException {

//        2017-03-01 17:05:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(ori);
        return date.getTime();
    }
}
