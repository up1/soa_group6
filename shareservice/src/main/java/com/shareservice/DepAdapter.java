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
public class DepAdapter {

    public List<Department> getDepartmentAll(){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://35.187.208.148:8091/departments";
        ResponseEntity<Department[]> departmentList = restTemplate.getForEntity(url, Department[].class);
        return Arrays.asList(departmentList.getBody());

    }

    public Department getDepartmentById(int departmentId){
        RestTemplate restTemplate =  new RestTemplate();
        String url = "http://35.187.208.148:8091/departments/" + departmentId;
        Department department = restTemplate.getForObject(url, Department.class);
        return department;

    }
}
