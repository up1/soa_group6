package com.teamsmokeweed;

import com.teamsmokeweed.model.getalldoc.dep.GetDepNameResponse;
import com.teamsmokeweed.model.getalldoc.share.ShareAdapter;
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

//    @GetMapping("/documents")
//    public List<GetAllDoc> GetRecentDoc() {
//        return this.docRepository.GetAllDoc();
//    }

    @GetMapping("/documents/all/{keys}")
    public List<Map<String, Object>> getDocument(@RequestParam(value = "userID", defaultValue = "0") int r_user_id,
                                                 @RequestParam(value = "order", defaultValue = "ASC") String r_order,
                                                 @RequestParam(value = "orderBy", defaultValue = "id") String r_orderBy,
                                                 @PathVariable(value = "keys") String k,
                                                 @RequestHeader("Authorization") String authorization){
        //split ("Bearer ") and get token

        Map<String, Object> request = new HashMap<>();
        request.put("userID", r_user_id);
        request.put("order", r_order);
        request.put("orderBy", r_orderBy);
        String[] authorizationSplit = authorization.split("Bearer ");
        String token = authorizationSplit[1];
//        0->recent
//        1->message
        int key = 0;
        if (k.equals("message")){
            key = 1;
        }
//        System.out.println(key);
        List<String> orderRequest = Arrays.asList("id","tag", "title", "description", "lastUpdated");
        List<String> orderResponse = Arrays.asList("doc_id","doc_tag", "doc_title", "doc_desc", "doc_date");
//        AES, DESC
        String order = request.get("order").toString();
//        id, tag, title
        String orderBy = orderResponse.get(orderRequest.indexOf(request.get("orderBy").toString()));
        return this.docRepository.getDocument(key,(Integer) request.get("userID"), order, orderBy, token);

    }
//    @PostMapping("/test")
//    public void PostFile(@RequestParam("file") MultipartFile mfile){
//
//        UploadAdapter uploadAdapter = new UploadAdapter();
//        uploadAdapter.getDepName(mfile);
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
//    public Map<String, Object> createDocument(@RequestBody Map<String, Object> obj){
//        return this.docRepository.createDocument(obj);
//    }
    @PostMapping(value = "/documents")
    public Map<String, Object> createDocument(@RequestParam("title") String title,
                                              @RequestParam("description") String des,
                                              @RequestParam("tag") String tag,
                                              @RequestParam("user_id") int id,
                                              @RequestParam("file") MultipartFile[] multipartFiles
                                              ){
        return this.docRepository.createDocument(title, des, tag, id, multipartFiles);
    }


    @GetMapping(value = "/documents/{id}")
    public Map<String, Object> getDocumentByIdDoc(@PathVariable(value = "id") int doc_id){
        return this.docRepository.getDocumentByIdDoc(doc_id);
    }

    @PutMapping(value = "/documents/{id}")
    public Map<String, Object> updateDocument(
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
        return this.docRepository.updateDocument(doc_id, title, des, tag, multipartFiles);
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
    public GetDepNameResponse ownerDepartment(@PathVariable(value = "id") int doc_id){
        return this.docRepository.ownerDepartment(doc_id);
    }

    @DeleteMapping(value = "/documents/{id}")
    public Map<String, Object> deleteDocument(@PathVariable(value = "id") int doc_id,
                                              @RequestHeader("Authorization") String authorization){
        String[] authorizationSplit = authorization.split("Bearer ");
        String token = authorizationSplit[1];
        ShareAdapter shareAdapter = new ShareAdapter();
        shareAdapter.deleteShare(doc_id, token);
        return this.docRepository.deleteDocument(doc_id);
    }

}
