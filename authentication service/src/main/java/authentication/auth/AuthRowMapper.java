package authentication.auth;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 7/3/2560.
 */
public class AuthRowMapper implements RowMapper<UserPass> {

    @Override
    public UserPass mapRow(ResultSet resultSet, int rowNumber) throws SQLException

    {
        UserPass userPass = new UserPass();

        userPass.setUser_id(resultSet.getInt("user_id"));
//        userPass.setUser_username(resultSet.getString("user_username"));
//        userPass.setUser_password(resultSet.getString("user_password"));


        return userPass;
    }
}
