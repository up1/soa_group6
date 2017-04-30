package com.teamsmokeweed;

import com.teamsmokeweed.model.check.unique.username.CheckUniqueUsernameResponse;
import com.teamsmokeweed.model.dep.DepAdapter;
import com.teamsmokeweed.model.postuser.PostUserRequest;
import com.teamsmokeweed.model.putuser.PutSelfUserUpdateRequest;
import com.teamsmokeweed.model.putuser.PutUserUpdateRequest;
import com.teamsmokeweed.model.userinfo.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jongzazaal on 13/4/2560.
 */

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private DepAdapter depAdapter;

    public UserRepository(DepAdapter depAdapter) {
        this.depAdapter = depAdapter;
    }

    public String md5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1,m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getUser(UserInfoRequest userRequest){


//        UserInfoResponse userInfoResponse = jdbcTemplate.queryForObject("SELECT user_id, dep_id, user_role, user_ispasswordchange, user_username, user_fname, user_lname FROM users WHERE user_username = ? AND user_password = ?",
//                new Object[]{userRequest.getUsername(), userRequest.getPassword()}, new UserInfoResponseRowMapping());
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT user_id AS id, user_username AS username, user_fname AS first_name, user_lname AS last_name, dep_id, user_role AS role, user_ispasswordchange AS password_changed FROM users WHERE user_username = ? AND user_password = ?",
                new Object[]{userRequest.getUsername(), userRequest.getPassword()});

        try{

            result.put("department", depAdapter.GetDepName((Integer) result.get("dep_id")));
            result.remove("dep_id");
//            userInfoResponse.setDep_name(depAdapter.GetDepName(userInfoResponse.getDep_id()).getDep_name());
            return result;
        }
        catch (Exception e){
//            userInfoResponse.setDep_name("department-service is crash");
            return new HashMap<>();
        }

    }

    public void PostUser(PostUserRequest postUserRequest){

        jdbcTemplate.update("INSERT INTO users(user_username, user_password, user_fname, user_lname, dep_id, user_role, user_ispasswordchange) VALUES(?,?,?,?,?,?,?)",
                new Object[]{postUserRequest.getUsername(), postUserRequest.getPassword(),
                        postUserRequest.getFirst_name(), postUserRequest.getLast_name(), postUserRequest.getDepartment().getId(),
                        postUserRequest.getUser_role(), postUserRequest.getUser_ispasswordchange()});

    }
    public void DeleteUser(int user_id){

        jdbcTemplate.update("DELETE FROM users WHERE user_id=?", user_id);

    }

    public void PutSelfUserUpdate(PutSelfUserUpdateRequest putSelfUserUpdateRequest){

        jdbcTemplate.update("UPDATE users SET user_username = ?, user_password = ?, user_ispasswordchange = 1 WHERE user_id= ?", new Object[]{putSelfUserUpdateRequest.getUsername(), putSelfUserUpdateRequest.getPassword(), putSelfUserUpdateRequest.getUserID()});

    }

    public CheckUniqueUsernameResponse checkUniqueUsername(String username){

        int count = jdbcTemplate.queryForObject("SELECT count(user_username) FROM users WHERE user_username = ?",
                new Object[]{username}, Integer.class);

        if(count>=1){
            return new CheckUniqueUsernameResponse(false);
        }
        else {
            return new CheckUniqueUsernameResponse(true);
        }

    }
    public Map<String, Object> DebNameByUserID(int user_id){
//        UserInfoResponse userInfoResponse = jdbcTemplate.queryForObject("SELECT user_id, dep_id, user_role, user_ispasswordchange, user_username, user_fname, user_lname FROM users WHERE user_id = ?",
//                new Object[]{user_id}, new UserInfoResponseRowMapping());
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT dep_id FROM users WHERE user_id = ?",
                new Object[]{user_id});
        try{
//            GetDepNameResponse response = depAdapter.GetDepName(userInfoResponse.getDep_id());
//            GetDepNameResponse getDepNameResponse = depAdapter.GetDepName(userInfoResponse.getDep_id());
//            userInfoResponse.setDep_name();
            Map<String, Object> resultDep = depAdapter.GetDepName((Integer) result.get("dep_id"));

            return resultDep;
        }
        catch (Exception e){
//            GetDepNameResponse getDepNameResponse = new GetDepNameResponse(0,"department-service is crash");
//            return getDepNameResponse;
            return new HashMap<>();
        }
    }
    public Map<String, Object> GetUserInfo(int userID){
        return jdbcTemplate.queryForMap("SELECT user_id AS id, user_username AS username, user_fname AS first_name, user_lname AS last_name, dep_id, user_role AS role, user_ispasswordchange AS password_changed FROM users WHERE user_id = ?",
                userID);

    }
    public void PutUserUpdate(PutUserUpdateRequest putUserUpdateRequest){
        jdbcTemplate.update("UPDATE users SET user_username = ?, user_fname = ?, user_lname = ?, dep_id = ?  WHERE user_id= ?",
                new Object[]{putUserUpdateRequest.getUsername(), putUserUpdateRequest.getFirst_name(), putUserUpdateRequest.getLast_name(), putUserUpdateRequest.getDepartment().getId(), putUserUpdateRequest.getId()});
    }
}
