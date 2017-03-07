package authentication;

import authentication.auth.AuthRepository;
import authentication.auth.UserPass;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody String  postUserInfo(@RequestBody UserPass userPass){
        authRepository.isUser(userPass);
        return String.format("{\"response\":%S}", authRepository.isUser(userPass).toString());
    }
}
