package com.shareservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by melon on 4/24/2017.
 */

@Service
public class DocAdapter {

    public List<Document> getDocumentAll(){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://35.187.208.148:8093/documents";
        ResponseEntity<Document[]> documentList = restTemplate.getForEntity(url, Document[].class);
        return Arrays.asList(documentList.getBody());

    }
}
