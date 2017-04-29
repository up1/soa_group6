package com.shareservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by melon on 4/24/2017.
 */

@Service
public class DocAdapter {

    public List<Map<String, Object>> getDocumentAll(){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8093/documents";
        List<Map<String, Object>> documentList = restTemplate.getForObject(url, List.class);
        return documentList;
    }

    public Map<String, Object> getOwnerByDoc(int documentId){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8093/OwnerDepartment/" + documentId;
        Map<String, Object> department= restTemplate.getForObject(url, Map.class);
        return department;
    }
}
