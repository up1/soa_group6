package com.shareservice.Adapter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by melon on 4/24/2017.
 */

@Service
public class DocAdapter {

    public List<Map<String, Object>> getDocumentAll(String token){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8093/documents/all/recent";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        List<Map<String, Object>> documentList = restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
        return documentList;
    }

    public Map<String, Object> getOwnerByDoc(int documentId){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8093/OwnerDepartment/" + documentId;
        Map<String, Object> department= restTemplate.getForObject(url, Map.class);
        return department;
    }
}
