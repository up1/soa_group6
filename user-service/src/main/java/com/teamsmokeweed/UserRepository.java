package com.teamsmokeweed;

import com.teamsmokeweed.model.check.unique.username.CheckUniqueUsernameResponse;
import com.teamsmokeweed.model.dep.DepAdapter;
import com.teamsmokeweed.model.postuser.PostUserRequest;
import com.teamsmokeweed.model.putuser.PutSelfPasswordUpdateRequest;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT user_id AS id, user_username AS username, user_fname AS first_name, user_lname AS last_name, dep_id, user_role AS role, user_ispasswordchange AS password_changed FROM users WHERE user_username = ? AND user_password = ?",
                new Object[]{userRequest.getUsername(), userRequest.getPassword()});

        try{

            result.put("department", depAdapter.getDepName((int) result.get("dep_id")));
            result.remove("dep_id");
            return result;
        }
        catch (Exception e){

            return new HashMap<>();
        }

    }

    public void postUser(PostUserRequest postUserRequest){

        jdbcTemplate.update("INSERT INTO users(user_username, user_password, user_fname, user_lname, dep_id, user_role, user_ispasswordchange) VALUES(?,?,?,?,?,?,?)",
                new Object[]{postUserRequest.getUsername(), postUserRequest.getPassword(),
                        postUserRequest.getFirst_name(), postUserRequest.getLast_name(), postUserRequest.getDepartment().getId(),
                        postUserRequest.getUser_role(), postUserRequest.getUser_ispasswordchange()});

    }
    public void deleteUser(int user_id){

        jdbcTemplate.update("DELETE FROM users WHERE user_id=?", user_id);

    }

    public void putSelfUserUpdate(PutSelfUserUpdateRequest putSelfUserUpdateRequest){

        jdbcTemplate.update("UPDATE users SET user_username = ? WHERE user_id= ?", new Object[]{putSelfUserUpdateRequest.getUsername(), putSelfUserUpdateRequest.getId()});

    }
    public void putSelfPasswordUpdate(PutSelfPasswordUpdateRequest putSelfPasswordUpdateRequest){
        jdbcTemplate.update("UPDATE users SET user_password = ?, user_ispasswordchange = 1 WHERE user_id= ?", new Object[]{putSelfPasswordUpdateRequest.getNewPassword(), putSelfPasswordUpdateRequest.getId()});
    }

    public boolean checkPassword(int id, String password){
        int count = jdbcTemplate.queryForObject("SELECT count(user_username) FROM users WHERE user_id = ? AND user_password = ?",
                new Object[]{id, password}, Integer.class);

        if(count>=1){
            return true;
        }
        else {
            return false;
        }
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
    public Map<String, Object> debNameByUserID(int user_id){

        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT dep_id FROM users WHERE user_id = ?",
                new Object[]{user_id});
        try{

            Map<String, Object> resultDep = depAdapter.getDepName((Integer) result.get("dep_id"));

            return resultDep;
        }
        catch (Exception e){

            return new HashMap<>();
        }
    }
    public Map<String, Object> getUserInfo(int userID){
        return jdbcTemplate.queryForMap("SELECT user_id AS id, user_username AS username, user_fname AS first_name, user_lname AS last_name, dep_id, user_role AS role, user_ispasswordchange AS password_changed FROM users WHERE user_id = ?",
                userID);

    }
    public List<Map<String, Object>> getAllUserInfo(){
        return jdbcTemplate.queryForList("SELECT user_id AS id, user_username AS username, user_fname AS first_name, user_lname AS last_name, dep_id, user_role AS role, user_ispasswordchange AS password_changed FROM users");

    }
    public void putUserUpdate(PutUserUpdateRequest putUserUpdateRequest){
        jdbcTemplate.update("UPDATE users SET user_username = ?, user_fname = ?, user_lname = ?, dep_id = ?  WHERE user_id= ?",
                new Object[]{putUserUpdateRequest.getUsername(), putUserUpdateRequest.getFirst_name(), putUserUpdateRequest.getLast_name(), putUserUpdateRequest.getDepartment().getId(), putUserUpdateRequest.getId()});
    }
    public String resetPwd(int userID){
        String uniqueID = UUID.randomUUID().toString().substring(0, 6);
        String encPass = md5(uniqueID);
        jdbcTemplate.update("UPDATE users SET user_password = ?, user_ispasswordchange = 0 WHERE user_id = ?",
                new Object[]{encPass, userID});
        return  uniqueID;
    }
}
