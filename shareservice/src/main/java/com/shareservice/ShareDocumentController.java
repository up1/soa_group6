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

    @DeleteMapping("/share/revoke/")
    public @ResponseBody
    ResponseEntity<ServiceStatus> revokeDepartmentFromDoc (@RequestParam(value="doc_id") int doc_id,
                                         @RequestParam(value="dep_id")int dep_id){
        ServiceStatus status = shareDocumentRepository.revokeDepartmentFromDoc(doc_id, dep_id);
        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/share/addDep/")
    public @ResponseBody
    ResponseEntity<ServiceStatus> addDepartmentToDoc (@RequestParam(value="doc_id") int doc_id,
                             @RequestParam(value="dep_id") int dep_id){
        ServiceStatus status = shareDocumentRepository.postShareToOtherDepartment(doc_id, dep_id);
        if(status.isResponse()){
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<ServiceStatus>(status, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/share/department/")
    public @ResponseBody
    ResponseEntity<List<Department>> getShareDepartmentByDoc
            (@RequestParam (value = "doc_id") int doc_id){
        List<Department> listdep;
        listdep = shareDocumentRepository.getListShareDepartmentByDoc(doc_id);
        if(listdep.size() > 0){
            return new ResponseEntity<List<Department>>(listdep, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<List<Department>>(new ArrayList<Department>(){}, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/department/expect")
    public @ResponseBody
    ResponseEntity<List<Department>> getListDepartmentExceptMe
            (@RequestParam (value = "dep_id") int dep_id){
        List<Department> listdep;
        listdep = shareDocumentRepository.getListDepartmentExceptMe(dep_id);
        if(listdep.size() > 0){
            return new ResponseEntity<List<Department>>(listdep, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<List<Department>>(new ArrayList<Department>(){}, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/department")
    public @ResponseBody
    ResponseEntity<List<Department>> getListDepartmentAll
            (){
        List<Department> listdep;
        listdep = shareDocumentRepository.getListDepartmentAll();
        if(listdep.size() > 0){
            return new ResponseEntity<List<Department>>(listdep, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<List<Department>>(new ArrayList<Department>(){}, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/trace")
    public String p(){
                return "abc";
    }



}
