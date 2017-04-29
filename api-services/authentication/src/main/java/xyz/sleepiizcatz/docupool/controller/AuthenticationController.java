package xyz.sleepiizcatz.docupool.controller;

import io.jsonwebtoken.SignatureException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.sleepiizcatz.docupool.service.AuthenticationService;
import xyz.sleepiizcatz.docupool.service.TokenService;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/auth", produces = "application/vnd.api+json")
    public String getToken(@RequestBody Map<String, String> credentials) throws IOException {
        Map<String, Object> user = this.authenticationService.getIdentity(credentials);
        if (user != null) {
            return new JSONObject()
                    .put("token", TokenService.generate(user))
                    .toString();
        }

        return new JSONObject()
                .put("error", new JSONObject()
                    .put("message", "Invalid username or password"))
                .toString();
    }

    @GetMapping(value = "/auth", produces = "application/vnd.api+json")
    public Object checkToken(@RequestParam String token) {
        try {
            return TokenService.parse(token);
        } catch (SignatureException e) { }

        return new JSONObject()
                .put("error", new JSONObject()
                        .put("message", "Invalid token"))
                .toString();
    }
}
