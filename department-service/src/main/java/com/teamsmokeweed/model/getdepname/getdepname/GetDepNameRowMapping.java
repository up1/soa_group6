package com.teamsmokeweed.model.getdepname.getdepname;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetDepNameRowMapping implements RowMapper<GetDepNameResponse>{
    @Override
    public GetDepNameResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GetDepNameResponse(rs.getString("dep_name"));
    }
}
