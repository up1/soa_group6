package authentication.auth;

import authentication.auth.UserPass.AuthRowMapper;
import authentication.auth.UserPass.UserPassResult;
import authentication.auth.whoIsUser.WhoIsUserResult;
import authentication.auth.whoIsUser.WhoIsUserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jongzazaal on 6/3/2560.
 */


@Repository
public class AuthRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public Boolean isUser(UserPassResult userPass) {

        List<UserPassResult> listUser = jdbcTemplate.query("SELECT user_username FROM users WHERE user_username = ? AND user_password = ?"
                , new Object[]{userPass.getUser_username(), userPass.getUser_password()}, new AuthRowMapper());

        if(listUser.size()>0){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public WhoIsUserResult whoIsUser(String user_id) {

        List<WhoIsUserResult> listUser = jdbcTemplate.query("" +
                        "SELECT u.user_id, u.user_username, u.user_fname, u.user_lname, u.dep_id, d.dep_name\n" +
                        "FROM users u\n" +
                        "JOIN dep d\n" +
                        "ON u.dep_id = d.dep_id\n" +
                        "WHERE u.user_id = ?;"
                , new Object[]{user_id}, new WhoIsUserRowMapper());

        if(listUser.size()>0){
            return listUser.get(0);
        }
        return new WhoIsUserResult();
    }

    @Transactional(readOnly = true)
    public WhoIsUserResult whoIsUser(String user_username, String user_password) {

        List<WhoIsUserResult> listUser = jdbcTemplate.query("" +
                        "SELECT u.user_id, u.user_username, u.user_fname, u.user_lname, u.dep_id, d.dep_name\n" +
                        "FROM users u\n" +
                        "JOIN dep d\n" +
                        "ON u.dep_id = d.dep_id\n" +
                        "WHERE u.user_username = ? AND u.user_password = ?;"
                , new Object[]{user_username, user_password}, new WhoIsUserRowMapper());

        if(listUser.size()>0){
            return listUser.get(0);
        }
        return new WhoIsUserResult();
    }


    @Transactional(readOnly = true)
    public Boolean isAdmin(UserPassResult userPass) {

        List<UserPassResult> listUser = jdbcTemplate.query("SELECT user_id FROM admin WHERE user_id = ? "
                , new Object[]{userPass.getUser_id()}, new AuthRowMapper());

        if(listUser.size()>0){
            return true;
        }
        return false;
    }


}
