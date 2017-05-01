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

        Map<String, Object> userDepartment = (Map<String, Object>) user.get("department");

        String userJson = new JSONObject()
                .put("id", user.get("id"))
                .put("username", user.get("username"))
                .put("admin", (Integer) user.get("role") == 0 ? false : true)
                .put("passwordChanged", (Integer) user.get("password_changed") == 0 ? false : true)
                .put("firstName", user.get("first_name"))
                .put("lastName", user.get("last_name"))
                .put("department", new JSONObject()
                        .put("id", userDepartment.get("id"))
                        .put("name", userDepartment.get("name")))
                .toString();

        return Jwts.builder()
                .setSubject((String) user.get("username"))
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
