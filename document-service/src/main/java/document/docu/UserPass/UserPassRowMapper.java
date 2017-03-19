package document.docu.UserPass;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 19/3/2560.
 */
public class UserPassRowMapper implements RowMapper<UserPassResult> {

    @Override
    public UserPassResult mapRow(ResultSet resultSet, int rowNumber) throws SQLException

    {
        UserPassResult userPass = new UserPassResult();
        userPass.setUser_username(resultSet.getString("user_username"));


        return userPass;
    }
}
