package com.shareservice.Adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by melon on 4/24/2017.
 */

@Service
public class DepAdapter {

    public List<Map<String, Object>> getDepartmentAll(){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8091/departments";
        List<Map<String, Object>> departmentList = restTemplate.getForObject(url, List.class);
        return departmentList;

    }

    public Map<String, Object> getDepartmentById(int departmentId){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://localhost:8091/departments/" + departmentId;
        Map<String, Object> department = restTemplate.getForObject(url, Map.class);
        return department;

    }
}
