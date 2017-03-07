package authentication.auth;

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
    public Boolean isUser(UserPass userPass) {

        List<UserPass> listUser = jdbcTemplate.query("SELECT user_id FROM users WHERE user_username = ? AND user_password = ?"
                , new Object[]{userPass.getUser_username(), userPass.getUser_password()}, new AuthRowMapper());

        if(listUser.size()>0){
            System.out.println(""+listUser.get(0).getUser_id());
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Boolean isAdmin(UserPass userPass) {

        List<UserPass> listUser = jdbcTemplate.query("SELECT user_id FROM admin WHERE user_id = ? "
                , new Object[]{userPass.getUser_id()}, new AuthRowMapper());

        if(listUser.size()>0){
            return true;
        }
        return false;
    }


}
