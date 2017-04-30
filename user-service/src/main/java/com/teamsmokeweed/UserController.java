package com.teamsmokeweed;

import com.teamsmokeweed.model.check.unique.username.CheckUniqueUsernameResponse;
import com.teamsmokeweed.model.deleteuser.DeleteUserRequest;
import com.teamsmokeweed.model.dep.DepAdapter;
import com.teamsmokeweed.model.postuser.PostUserRequest;
import com.teamsmokeweed.model.putresetpwd.PutResetPwd;
import com.teamsmokeweed.model.putuser.PutSelfUserUpdateRequest;
import com.teamsmokeweed.model.putuser.PutUserUpdateRequest;
import com.teamsmokeweed.model.userinfo.UserInfoRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
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
    Map<String, Object> UserInfo(@RequestBody UserInfoRequest userRequest){

        //password md5
        userRequest.setPassword(userRepository.md5(userRequest.getPassword()));
        return this.userRepository.getUser(userRequest);
    }

    @PostMapping("/users")
    public @ResponseBody
    JSONObject PostUser(@RequestBody PostUserRequest postUserRequest){
        if(!(this.userRepository.checkUniqueUsername(postUserRequest.getUsername()).isUnique())){
            //return  new ResponseEntity<PostUserResponse>(new PostUserResponse("Username is already Exists!"), HttpStatus.OK);
            JSONObject err = new JSONObject();
            JSONObject msg = new JSONObject();
            msg.put("message", "User cannot be created!");
            err.put("error", msg);
            return err;
        }
        String uniqueID = UUID.randomUUID().toString().substring(0, 6);
        postUserRequest.setPassword(uniqueID);
        postUserRequest.setPassword(userRepository.md5(postUserRequest.getPassword()));
        this.userRepository.PostUser(postUserRequest);
        //return new ResponseEntity<PostUserResponse>(new PostUserResponse(postUserRequest.getUser_username(), uniqueID, "User has been created!"), HttpStatus.OK);
        JSONObject succ = new JSONObject();
        JSONObject msg = new JSONObject();
        msg.put("username", postUserRequest.getUsername());
        msg.put("password", postUserRequest.getPassword());
        msg.put("message", "User has been created!");
        succ.put("success", msg);
        return succ;
    }

    @GetMapping("/UniqueUsername")
    public CheckUniqueUsernameResponse CheckUniqueUsername(@RequestParam(value = "username") String username){
        return this.userRepository.checkUniqueUsername(username);
    }

    @DeleteMapping("/users")
    public @ResponseBody
    JSONObject DeleteUser(@RequestBody DeleteUserRequest deleteUserRequest){
        JSONObject msg = new JSONObject();
        try{
            this.userRepository.DeleteUser(deleteUserRequest.getId());
            msg.put("message", "User has been deleted!");
        } catch (Exception e){
            msg.put("message", "Error! User cannot delete!");
        }
        return msg;
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

    @GetMapping("/users/{id}")
    public JSONObject GetUserInfo(@PathVariable int id){
        JSONObject userInfo = new JSONObject();
        try {
            Map<String, Object> rawMapUserInfo = this.userRepository.GetUserInfo(id);
            userInfo.put("id", id);
            userInfo.put("username", rawMapUserInfo.get("username"));
            userInfo.put("first_name", rawMapUserInfo.get("first_name"));
            userInfo.put("last_name", rawMapUserInfo.get("last_name"));
            userInfo.put("role", rawMapUserInfo.get("role"));
            userInfo.put("department", new DepAdapter().GetDepName((int) rawMapUserInfo.get("dep_id")));
            userInfo.put("password_changed", ((int) rawMapUserInfo.get("password_changed") != 0));
        } catch (Exception e){
            userInfo.clear();
            userInfo.put("Error", "User not found! or Department service not available.");
        }
        return userInfo;
    }

    @PutMapping("/users")
    public @ResponseBody
    JSONObject PutUser(@RequestBody PutUserUpdateRequest putUserUpdateRequest){
        JSONObject msg = new JSONObject();
        try{
            this.userRepository.PutUserUpdate(putUserUpdateRequest);
            msg.put("message", "User information has been updated!");
        } catch (Exception e){
            msg.put("message", "Error! user cannot be updated!");
        }
        return msg;

    }

    @PutMapping("/users/selfUpdate")
    public @ResponseBody
    JSONObject PutSelfUserUpdate(@RequestBody PutSelfUserUpdateRequest putSelfUserUpdateRequest){
        JSONObject msg = new JSONObject();
        try{
            putSelfUserUpdateRequest.setPassword(userRepository.md5(putSelfUserUpdateRequest.getPassword()));
            this.userRepository.PutSelfUserUpdate(putSelfUserUpdateRequest);
            msg.put("message", "Your information has been updated!");
        } catch (Exception e){
            msg.put("message", "Error!");
        }
        return msg;

    }
    @PutMapping("/users/resetPwd")
    public @ResponseBody
    JSONObject PutSelfUserUpdate(@RequestBody PutResetPwd putResetPwd){
        JSONObject msg = new JSONObject();
        try{
            this.userRepository.ResetPwd(putResetPwd.getId());
            msg.put("message", "Password has reset!");
            return msg;
        } catch (Exception e){
            msg.put("message", "Error! Cannot reset password!");
            return msg;
        }


    }

    @GetMapping("/debNameByUserID")
    public Map<String, Object> DebNameByUserID(@RequestParam(value = "userID") int userID){
        return this.userRepository.DebNameByUserID(userID);
    }




}
