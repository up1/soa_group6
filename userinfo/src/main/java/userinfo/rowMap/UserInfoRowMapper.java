package userinfo.rowMap;

import org.springframework.jdbc.core.RowMapper;
import userinfo.userInfo.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 6/3/2560.
 */
public class UserInfoRowMapper implements RowMapper<UserInfo> {

    @Override
    public UserInfo mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        UserInfo userInfo = new UserInfo();

        System.out.println(""+resultSet.getString("user_id"));
        userInfo.setUser_id(resultSet.getInt("user_id"));
        userInfo.setUser_username(resultSet.getString("user_username"));
        userInfo.setUser_firstname_th(resultSet.getString("user_fname_th"));
        userInfo.setUser_lastname_th(resultSet.getString("user_lname_th"));
        userInfo.setUser_firstname_en(resultSet.getString("user_fname_en"));
        userInfo.setUser_lastname_en(resultSet.getString("user_fname_en"));
        userInfo.setUser_department(resultSet.getInt("dep_id"));

        return userInfo;
    }
}