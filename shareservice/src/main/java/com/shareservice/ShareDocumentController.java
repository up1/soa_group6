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

    @DeleteMapping("/documents/{documentId}/shares")
    public @ResponseBody
    ResponseEntity<ServiceStatus> revokeDepartmentFromDoc (@PathVariable String documentId,
                                                           @RequestBody Map<String, Integer> departmentId){
        ServiceStatus status = shareDocumentRepository.revokeDepartmentFromDoc(Integer.parseInt(documentId), departmentId.get("departmentId"));
        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/documents/{documentId}/shares")
    public @ResponseBody
    ResponseEntity<ServiceStatus> addDepartmentToDoc (@PathVariable String documentId,
                                                      @RequestBody Map<String, Integer> departmentId){
        ServiceStatus status = shareDocumentRepository.postShareToOtherDepartment(Integer.parseInt(documentId), departmentId.get("departmentId"));

        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/documents/{documentId}/shares")
    public @ResponseBody
    ResponseEntity<List<DepartmentStatus>> getListDepartmentStatusByDoc
            (@PathVariable String documentId){

        List<DepartmentStatus> listDepartmentWithStatusByDoc = shareDocumentRepository.getListDepartmentWithStatusByDoc(Integer.parseInt(documentId));
        if(listDepartmentWithStatusByDoc.size() > 0){
            return new ResponseEntity<List<DepartmentStatus>>(listDepartmentWithStatusByDoc, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<List<DepartmentStatus>>(new ArrayList<DepartmentStatus>(){}, HttpStatus.NOT_FOUND);
        }
    }

}


