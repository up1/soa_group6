package document;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jongzazaal on 18/3/2560.
 */
@Repository
public class DocumentRepository {

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
}
