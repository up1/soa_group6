package com.teamsmokeweed.model.getalldoc.dep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jongzazaal on 17/4/2560.
 */
@Service
public class DepAdapter {
    public GetDepNameResponse getDepName(int user_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8092/debNameByUserID?userID=" +user_id;
        GetDepNameResponse getDepNameResponse = restTemplate.getForObject(url, GetDepNameResponse.class);
        return getDepNameResponse;
    }

    public Map<String, Object> getDepepartment(int user_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8092/debNameByUserID?userID=" +user_id;
        Map<String, Object> result = restTemplate.getForObject(url, Map.class);
        return result;
    }

}
