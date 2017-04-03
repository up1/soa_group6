package authentication;

import authentication.auth.UserPass.UserPassResult;
import authentication.auth.whoIsUser.WhoIsUserResult;
import authentication.token.Token;
import authentication.token.TokenResult;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

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

        if(authRepository.isUser(userPass)){
            WhoIsUserResult whoIsUserResult =  authRepository.whoIsUser(userPass.getUser_username(),
                    userPass.getUser_password());
            return new ResponseEntity<>(whoIsUserResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new WhoIsUserResult(), HttpStatus.NO_CONTENT);
    }

    Key key = MacProvider.generateKey();
    @PostMapping("/genToken")
    public @ResponseBody TokenResult genToken(@RequestBody Token token){
        String compactJws = Jwts.builder()
                .claim("username", token.getUsername())
                .claim("password", token.getPassword())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        Claims c = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody();

        Token t = new Gson().fromJson(c.toString(), Token.class);

        System.out.println(t.getUsername()+"//"+t.getPassword());
        return new TokenResult(compactJws);
    }
}
