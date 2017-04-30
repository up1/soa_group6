package com.shareservice.Adapter;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by melon on 4/29/2017.
 */

@Service
public class TokenAdapter {
    public Map<String, Object> getDataByToken (String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://35.185.183.178:9000/auth?token=" + token;
        Map<String, Object> data = restTemplate.getForObject(url, Map.class);
        System.out.println("token adapter pass");
        return data;
    }
}
