package com.teamsmokeweed.model.getalldoc.files;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 24/4/2560.
 */
public class FilesAdapter {
    public List<Map<String, Object>> GetFileInfo(int doc_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8099/files?doc_id=" +doc_id;
//        List<FileResponse[]> fileResponse = restTemplate.getForEntity(url, FileResponse[].class);
        List<Map<String, Object>> result = restTemplate.getForObject(url, List.class);
//        System.out.println(result);

//        ResponseEntity<FileResponse[]> fileResponse = restTemplate.getForEntity(url, FileResponse[].class);



        return result;

    }
}
