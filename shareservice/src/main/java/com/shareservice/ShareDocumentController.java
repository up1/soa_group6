package com.shareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by super on 21/3/2560.
 */

@CrossOrigin
@Controller
@RestController
public class ShareDocumentController {

    private final ShareDocumentRepository shareDocumentRepository;

    @Autowired
    public ShareDocumentController(ShareDocumentRepository shareDocumentRepository) {
        this.shareDocumentRepository = shareDocumentRepository;
    }

    @DeleteMapping("/documents/{doc_id}/share")
    public @ResponseBody
    ResponseEntity<ServiceStatus> revokeDepartmentFromDoc (@PathVariable String doc_id,
                                                           @RequestBody Map<String, Integer> dep_id){
        ServiceStatus status = shareDocumentRepository.revokeDepartmentFromDoc(Integer.parseInt(doc_id), dep_id.get("dep_id"));
        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/documents/{doc_id}/share")
    public @ResponseBody
    ResponseEntity<ServiceStatus> addDepartmentToDoc (@PathVariable String doc_id,
                                                      @RequestBody Map<String, Integer> dep_id){
        ServiceStatus status = shareDocumentRepository.postShareToOtherDepartment(Integer.parseInt(doc_id), dep_id.get("dep_id"));
        System.out.println(dep_id.get("dep_id"));
        System.out.println(status.getMessage());

        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/documents/{doc_id}/share")
    public @ResponseBody
    ResponseEntity<List<Department>> getShareDepartmentByDoc
            (@PathVariable String doc_id){

        List<Department> listdep;
        listdep = shareDocumentRepository.getListShareDepartmentByDoc(Integer.parseInt(doc_id));
        if(listdep.size() > 0){
            return new ResponseEntity<List<Department>>(listdep, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<List<Department>>(new ArrayList<Department>(){}, HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/debug/documents/{documentId}/share")
    public Integer getDocument(@PathVariable String documentId, @RequestBody Map<String, Integer> department) {
        return department.get("departmentId");
    }



}
