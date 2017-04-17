package com.teamsmokeweed.model.dep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jongzazaal on 14/4/2560.
 */
@Service
public class DepAdapter {
    public GetDepNameResponse GetDepName(int dep_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8091/dep/" +dep_id;
        GetDepNameResponse getDepNameResponse = restTemplate.getForObject(url, GetDepNameResponse.class);
        return getDepNameResponse;
    }
}
