package authentication;

import authentication.auth.AuthRepository;
import authentication.auth.UserPass.UserPassResult;
import authentication.auth.whoIsUser.WhoIsUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jongzazaal on 6/3/2560.
 */
@CrossOrigin
@Controller
@RestController
public class AuthenticationController {

    private final AuthRepository authRepository;

    @Autowired
    public AuthenticationController(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    @PostMapping("/authentication")
    public @ResponseBody ResponseEntity<WhoIsUserResult> postUserInfo(@RequestBody UserPassResult userPass){
//        authRepository.isUser(userPass);
        if(authRepository.isUser(userPass)){
            WhoIsUserResult whoIsUserResult =  authRepository.whoIsUser(userPass.getUser_username(),
                    userPass.getUser_password());
            return new ResponseEntity<>(whoIsUserResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new WhoIsUserResult(), HttpStatus.NO_CONTENT);
    }
}
