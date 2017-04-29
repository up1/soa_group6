package xyz.sleepiizcatz.docupool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenService {

    private static AuthenticationService authenticationService;
    private static String secretKey = "be5a791366c5f2810d7dd6132dc0f06c";

    @Autowired
    private void setAuthenticationService(AuthenticationService authenticationService) {
        TokenService.authenticationService = authenticationService;
    }

    public static String generate(Map<String, Object> user) throws IOException {
        String userJson = new JSONObject()
                .put("id", user.get("user_id"))
                .put("username", user.get("user_username"))
                .put("admin", (Integer) user.get("user_role") == 0 ? false : true)
                .put("passwordChanged", (Integer) user.get("user_ispasswordchange") == 0 ? false : true)
                .put("firstName", user.get("user_fname"))
                .put("lastName", user.get("user_lname"))
                .put("department", new JSONObject()
                        .put("id", user.get("dep_id"))
                        .put("name", user.get("dep_name")))
                .toString();

        return Jwts.builder()
                .setSubject((String) user.get("user_username"))
                .setIssuedAt(Date.from(Instant.now()))
                .setIssuer("DocuPool")
                .claim("user", new ObjectMapper().readValue(userJson, HashMap.class))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public static Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
