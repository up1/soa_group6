package com.teamsmokeweed;

import com.teamsmokeweed.model.check.unique.username.CheckUniqueUsernameResponse;
import com.teamsmokeweed.model.dep.DepAdapter;
import com.teamsmokeweed.model.postuser.PostUserRequest;
import com.teamsmokeweed.model.userinfo.UserInfoRequest;
import com.teamsmokeweed.model.userinfo.UserInfoResponse;
import com.teamsmokeweed.model.userinfo.UserInfoResponseRowMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public UserInfoResponse getUser(UserInfoRequest userRequest){


        UserInfoResponse userInfoResponse = jdbcTemplate.queryForObject("SELECT user_id, dep_id, user_role, user_ispasswordchange, user_username, user_fname, user_lname FROM users WHERE user_username = ? AND user_password = ?",
                new Object[]{userRequest.getUsername(), userRequest.getPassword()}, new UserInfoResponseRowMapping());

        try{
            userInfoResponse.setDep_name(depAdapter.GetDepName(userInfoResponse.getDep_id()).getDep_name());
            return userInfoResponse;
        }
        catch (Exception e){
            userInfoResponse.setDep_name("department-service is crash");
            return userInfoResponse;
        }

    }

    public void PostUser(PostUserRequest postUserRequest){

        jdbcTemplate.update("INSERT INTO users(user_username, user_password, user_fname, user_lname, dep_id, user_role, user_ispasswordchange) VALUES(?,?,?,?,?,?,?)",
                new Object[]{postUserRequest.getUser_username(), postUserRequest.getUser_password(),
                        postUserRequest.getUser_fname(), postUserRequest.getUser_lname(), postUserRequest.getDep_id(),
                        postUserRequest.getUser_role(), postUserRequest.getUser_ispasswordchange()});

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
}
