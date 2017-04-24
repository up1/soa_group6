package com.shareservice;

import org.json.JSONObject;
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
    Map<String, Object> revokeDepartmentFromDoc (@PathVariable String documentId,
                                                           @RequestBody Map<String, Integer> departmentId){
        Map<String, Object> status = shareDocumentRepository.revokeDepartmentFromDoc(Integer.parseInt(documentId), departmentId.get("departmentId"));
        return status;

    }

    @PostMapping("/documents/{documentId}/shares")
    public @ResponseBody
    Map<String, Object> addDepartmentToDoc (@PathVariable String documentId,
                                                      @RequestBody Map<String, Integer> departmentId){
        Map<String, Object> status = shareDocumentRepository.postShareToOtherDepartment(Integer.parseInt(documentId), departmentId.get("departmentId"));
        return status;
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

    @GetMapping("/debug/shares")
    public @ResponseBody
    ResponseEntity<List<Map<String, Object>>> getShares (){
        return new ResponseEntity<List<Map<String, Object>>>(this.shareDocumentRepository.getShare(), HttpStatus.FOUND);
    }

}


