package com.teamsmokeweed.model.getdepname.getalldep;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetAllDepRowMapping implements RowMapper<GetAllDepResponse> {
    @Override
    public GetAllDepResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GetAllDepResponse(rs.getInt("dep_id"), rs.getString("dep_name"));
    }
}
