package com.shareservice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 21/3/2560.
 */
public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDep_id(rs.getInt("dep_id"));
        department.setDep_name(rs.getString("dep_name"));
        return department;
    }
}
