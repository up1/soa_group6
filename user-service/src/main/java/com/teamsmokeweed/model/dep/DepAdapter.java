package com.teamsmokeweed.model.dep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jongzazaal on 14/4/2560.
 */
@Service
public class DepAdapter {
    public Map<String, Object> GetDepName(int dep_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8091/departments/" +dep_id;
//        GetDepNameResponse getDepNameResponse = restTemplate.getForObject(url, GetDepNameResponse.class);
        Map<String, Object> result = restTemplate.getForObject(url, Map.class);
        return result;
    }
}
