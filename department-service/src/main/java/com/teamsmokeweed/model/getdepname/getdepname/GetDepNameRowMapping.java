package com.teamsmokeweed.model.getdepname.getdepname;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Armerino on 14/4/2560.
 */
public class GetDepNameRowMapping implements RowMapper<GetDepNameResponse>{

    private int dep_id;

    @Override
    public GetDepNameResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GetDepNameResponse(this.dep_id, rs.getString("dep_name"));
    }

    public GetDepNameRowMapping(int dep_id) {
        this.dep_id = dep_id;
    }
}
