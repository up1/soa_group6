package userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import userinfo.userInfo.UserInfo;
import userinfo.userInfo.UserInfoRepository;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jongzazaal on 6/3/2560.
 */
@CrossOrigin
@Controller
@RestController
public class UserinfoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserinfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping("/userinfo")
    public UserInfo getUserInfo(@RequestParam(value="userid", defaultValue="1") int userid) {

        return userInfoRepository.getUserInfoByID(userid);

    }

    @PostMapping("/userinfo")
    public @ResponseBody void  postUserInfo(@RequestBody UserInfo userInfo){
        userInfoRepository.postUserInfoByID(userInfo);
    }

    @DeleteMapping("/userinfo")
    public @ResponseBody void delUserInfo(@RequestBody UserInfo userInfo){
        userInfoRepository.deleteUserInfoByID(userInfo);
    }

    @PutMapping("userinfo")
    public @ResponseBody void putUserInfo(@RequestBody UserInfo userInfo){
        userInfoRepository.putUserInfoByID(userInfo);
    }


}