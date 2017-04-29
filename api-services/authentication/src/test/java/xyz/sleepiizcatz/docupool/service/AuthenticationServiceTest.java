package xyz.sleepiizcatz.docupool.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest(AuthenticationService.class)
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void existsIdentity() throws IOException {
        Map credentials = new ObjectMapper().readValue(
                new JSONObject()
                    .put("username", "melon")
                    .put("password", "melon")
                    .toString(),
                Map.class
        );

        Map user = null;
        try {
            user = this.authenticationService.getIdentity(credentials);
            System.out.println(user.toString());
        } catch (NullPointerException e) { }

        assertThat(user).isNotNull();
    }

    @Test
    public void notExistsIdentity() throws IOException {
        Map credentials = new ObjectMapper().readValue(
                new JSONObject()
                        .put("username", "leeroy")
                        .put("password", "jenkins")
                        .toString(),
                Map.class
        );

        Map user = null;
        try {
            user = this.authenticationService.getIdentity(credentials);
            System.out.println(user.toString());
        } catch (NullPointerException e) { }

        assertThat(user).isNull();
    }
}
