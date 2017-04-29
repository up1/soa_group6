package com.teamsmokeweed;

import com.teamsmokeweed.model.getalldoc.GetAllDoc;
import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jongzazaal on 15/4/2560.
 */
@CrossOrigin
@Controller
@RestController
public class DocController {
    private final DocRepository docRepository;

    @Autowired
    public DocController(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

//    @GetMapping("/documents")
//    public List<GetAllDoc> GetRecentDoc(@RequestParam(value = "userid") String userid){
//        return this.docRepository.GetAllDoc();
//    }

    @GetMapping("/documents")
    public List<GetAllDoc> GetRecentDoc() {
        return this.docRepository.GetAllDoc();
    }
//    @PostMapping("/test")
//    public void PostFile(@RequestParam("file") MultipartFile mfile){
//
//        UploadAdapter uploadAdapter = new UploadAdapter();
//        uploadAdapter.GetDepName(mfile);
//
//    }
    @PostMapping("test")
    public List<Map<String, Object>> test(@RequestBody Map <String,Object> a){
        Map<String, Object> map = new HashMap<>();
        map.put("a", "ccc");
        map.put("b", "ddd");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        map.replace("a", "asd");
        map = changValue(map, "a", "as");
        list.add(map);
        list.add(a);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("cv", 5);
        map2.put("list",list);
        return list;
    }

    public Map<String, Object> changValue(Map<String, Object> oldMap, String k, Object v){
        Map<String, Object> newMap = new HashMap<>();
        newMap.putAll(oldMap);
        newMap.replace(k, v);
        return newMap;
    }

//    @PostMapping(value = "/documents")
//    public Map<String, Object> CreateDocument(@RequestBody Map<String, Object> obj){
//        return this.docRepository.CreateDocument(obj);
//    }
    @PostMapping(value = "/documents")
    public Map<String, Object> CreateDocument(@RequestParam("title") String title,
                                              @RequestParam("description") String des,
                                              @RequestParam("tag") String tag,
                                              @RequestParam("user_id") int id,
                                              @RequestParam("file") MultipartFile[] multipartFiles
                                              ){
        return this.docRepository.CreateDocument(title, des, tag, id, multipartFiles);
    }

    @GetMapping(value = "/documents/{id}")
    public Map<String, Object> GetDocumentByIdDoc(@PathVariable(value = "id") int doc_id){
        return this.docRepository.GetDocumentByIdDoc(doc_id);
    }

    @PutMapping(value = "/documents/{id}")
    public Map<String, Object> UpdateDocument(
                                                @RequestParam(value = "title", required = true) String title,
                                                @RequestParam("description") String des,
                                                @RequestParam("tag") String tag,
                                                @PathVariable("id") int doc_id,
                                                @RequestParam("file") MultipartFile[] multipartFiles
    ){
//        System.out.println(title);
//        Map<String, Object> a = new HashMap<>();
//        a.put("title", title);
//        return a;
        return this.docRepository.UpdateDocument(doc_id, title, des, tag, multipartFiles);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                String method = request.getMethod().toLowerCase();
                //By default, only POST is allowed. Since this is an 'update' we should accept PUT.
                if (!Arrays.asList("put", "post").contains(method)) {
                    return false;
                }
                String contentType = request.getContentType();
                return (contentType != null &&contentType.toLowerCase().startsWith("multipart/"));
            }
        };
    }

    @GetMapping(value = "/OwnerDepartment/{id}")
    public GetDepNameResponse OwnerDepartment(@PathVariable(value = "id") int doc_id){
        return this.docRepository.OwnerDepartment(doc_id);
    }

}
