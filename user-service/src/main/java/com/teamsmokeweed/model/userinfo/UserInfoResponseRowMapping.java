package com.teamsmokeweed.model.userinfo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class UserInfoResponseRowMapping implements RowMapper<UserInfoResponse> {
    @Override
    public UserInfoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
//        user_id, dep_id, user_role, user_ispasswordchange, user_username, user_fname, user_lname
        userInfoResponse.setUser_id(rs.getInt("user_id"));
        userInfoResponse.setDep_id(rs.getInt("dep_id"));
        userInfoResponse.setUser_role(rs.getInt("user_role"));
        userInfoResponse.setUser_ispasswordchange(rs.getInt("user_ispasswordchange"));
        userInfoResponse.setUser_username(rs.getString("user_username"));
        userInfoResponse.setUser_fname(rs.getString("user_fname"));
        userInfoResponse.setUser_lname(rs.getString("user_lname"));
        return userInfoResponse;
    }
}
