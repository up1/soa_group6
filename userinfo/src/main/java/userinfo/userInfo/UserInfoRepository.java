package userinfo.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import userinfo.rowMap.UserInfoRowMapper;

/**
 * Created by jongzazaal on 6/3/2560.
 */
@Repository
public class UserInfoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public UserInfo getUserInfoByID(int userID) {
        UserInfo rows;
        rows = this.jdbcTemplate.queryForObject("SELECT user_id, user_username, user_fname_th, user_lname_th, " +
                "user_fname_en, user_lname_en, dep_id FROM users WHERE user_id = ?" , new Object[]{userID},
                new UserInfoRowMapper());
        return rows;
    }
    @Transactional(readOnly = false)
    public  void postUserInfoByID(UserInfo userInfo){
        this.jdbcTemplate.update("INSERT INTO users(user_username, user_password, user_fname_th,user_lname_th, " +
                "user_fname_en,user_lname_en, dep_id) VALUES(?,?,?,?,?,?,?)", new Object[]{userInfo.getUser_username()
                , userInfo.getUser_password(), userInfo.getUser_firstname_th(), userInfo.getUser_lastname_th(),
                userInfo.getUser_firstname_en(), userInfo.getUser_lastname_en(), userInfo.getUser_department()
               });
    }

    @Transactional(readOnly = false)
    public  void deleteUserInfoByID(UserInfo userInfo){
        this.jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", new Object[]{userInfo.getUser_id()
        });
    }

    @Transactional(readOnly = false)
    public  void putUserInfoByID(UserInfo userInfo){
        this.jdbcTemplate.update("UPDATE users SET user_username = ?, user_password = ?, user_fname_th = ?," +
                "user_lname_th = ?, user_fname_en = ?,user_lname_en = ?, dep_id = ? WHERE user_id = ?",
                new Object[]{userInfo.getUser_username(), userInfo.getUser_password(), userInfo.getUser_firstname_th(),
                        userInfo.getUser_lastname_th(), userInfo.getUser_firstname_en(), userInfo.getUser_lastname_en(),
                        userInfo.getUser_department(), userInfo.getUser_id()
        });
    }

}
