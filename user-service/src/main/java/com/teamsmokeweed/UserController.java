package com.teamsmokeweed;

import com.teamsmokeweed.model.check.unique.username.CheckUniqueUsernameResponse;
import com.teamsmokeweed.model.postuser.PostUserRequest;
import com.teamsmokeweed.model.postuser.PostUserResponse;
import com.teamsmokeweed.model.userinfo.UserInfoRequest;
import com.teamsmokeweed.model.userinfo.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * Created by jongzazaal on 13/4/2560.
 */
@CrossOrigin
@Controller
@RestController
public class UserController {


    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @GetMapping("/md5")
    public String md5(@RequestParam(value = "text", defaultValue = "0") String text){
        return this.userRepository.md5(text);
    }

    //whoisuser
    @PostMapping("/userInfo")
    public @ResponseBody
    ResponseEntity<UserInfoResponse> UserInfo(@RequestBody UserInfoRequest userRequest){

        //password md5
        userRequest.setPassword(userRepository.md5(userRequest.getPassword()));
        return new ResponseEntity<UserInfoResponse>(this.userRepository.getUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/user")
    public @ResponseBody
    JSONObject PostUser(@RequestBody PostUserRequest postUserRequest){
        if(!(this.userRepository.checkUniqueUsername(postUserRequest.getUser_username()).isUnique())){
            //return  new ResponseEntity<PostUserResponse>(new PostUserResponse("Username is already Exists!"), HttpStatus.OK);
            JSONObject err = new JSONObject();
            JSONObject msg = new JSONObject();
            msg.put("message", "User cannot be created!");
            err.put("error", msg);
        }
        String uniqueID = UUID.randomUUID().toString();
        postUserRequest.setUser_password(uniqueID);
        postUserRequest.setUser_password(userRepository.md5(postUserRequest.getUser_password()));
        this.userRepository.PostUser(postUserRequest);
        //return new ResponseEntity<PostUserResponse>(new PostUserResponse(postUserRequest.getUser_username(), uniqueID, "User has been created!"), HttpStatus.OK);
        JSONObject succ = new JSONObject();
        JSONObject msg = new JSONObject();
        msg.put("username", postUserRequest.getUser_username());
        msg.put("password", postUserRequest.getUser_password());
        msg.put("message", "User has been created!");
        succ.put("success", msg);
        return succ;
    }

    @GetMapping("/UniqueUsername")
    public CheckUniqueUsernameResponse CheckUniqueUsername(@RequestParam(value = "username") String username){
        return this.userRepository.checkUniqueUsername(username);
    }

    @GetMapping("/test")
    public JSONObject test(@RequestParam(value = "id") int id){
        JSONObject obj = new JSONObject();
        JSONObject errid = new JSONObject();
        errid.put("id", id);
        obj.put("char", "abc");
        obj.put("error", errid);
        return obj;
    }


}
