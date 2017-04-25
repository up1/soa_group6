package com.teamsmokeweed.model.getalldoc.files;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jongzazaal on 24/4/2560.
 */
public class FilesAdapter {
    public List<FileResponse> GetFileInfo(int doc_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8099/files?doc_id=" +doc_id;
//        List<FileResponse[]> fileResponse = restTemplate.getForEntity(url, FileResponse[].class);
        ResponseEntity<FileResponse[]> fileResponse = restTemplate.getForEntity(url, FileResponse[].class);


        return Arrays.asList(fileResponse.getBody());

    }
}
