package com.teamsmokeweed.model.getalldoc.share;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 30/4/2560.
 */
@Service
public class ShareAdapter {
    public List<Map<String, Object>> getShare(int doc_id, String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://35.187.208.148:8097/documents/"+doc_id+"/shares";

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer "+token);
//        HttpEntity<List<Map<String, Object>>> entity = new HttpEntity<>(headers);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        List<Map<String, Object>> result = restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();


//        System.out.println(result);
        return result;
    }
    public boolean isShare(List<Map<String, Object>> result, int dep_id){
//        System.out.println(result);
        boolean isShare = false;
        for (Map<String, Object> r: result) {
            int dep = (Integer) r.get("id");
            if(dep == dep_id){
                if((boolean)r.get("shared")){
                    isShare = true;
                    break;
                }

            }
        }
        return isShare;
    }
    public void DeleteShare(int doc_id,int dep_id, String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://35.187.208.148:8097/documents/"+doc_id+"/shares";

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer "+token);
//        HttpEntity<List<Map<String, Object>>> entity = new HttpEntity<>(headers);
        Map<String, Object> request = new HashMap<>();
        request.put("departmentId", dep_id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Map<String, Object> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class).getBody();
    }
}
