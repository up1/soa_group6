package authentication.auth.whoIsUser;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jongzazaal on 18/3/2560.
 */
public class WhoIsUserRowMapper implements RowMapper<WhoIsUserResult> {
    @Override
    public WhoIsUserResult mapRow(ResultSet rs, int rowNum) throws SQLException {

        WhoIsUserResult whoIsUserResult = new WhoIsUserResult();
        whoIsUserResult.setUser_id(rs.getInt("user_id"));
        whoIsUserResult.setUser_username(rs.getString("user_username"));
        whoIsUserResult.setUser_fname(rs.getString("user_fname"));
        whoIsUserResult.setUser_lname(rs.getString("user_lname"));
        whoIsUserResult.setDep_id(rs.getInt("dep_id"));
        whoIsUserResult.setDep_name(rs.getString("dep_name"));


        return whoIsUserResult;
    }
}
