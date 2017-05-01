package com.teamsmokeweed.model.getalldoc.upload;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by jongzazaal on 25/4/2560.
 */
@Service
public class UploadAdapter {
    //Create Doc
    public void Upload(MultipartFile multipartFile, int doc_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8099/upload";

        String oriFileName = multipartFile.getOriginalFilename();
        File tempFile = null;
        try {
            String extension = "." + getFileExtention(multipartFile.getOriginalFilename());
            tempFile = File.createTempFile("temp", extension);
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        tempFile = RenameFile(tempFile, oriFileName);
        map.add("file", new FileSystemResource(tempFile));
        map.add("doc_id", doc_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);


        try {
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
        } catch (Exception e) {
            e.getMessage();
        }
        tempFile.delete();

    }

    private String getFileExtention(String originalFilename) {
        return originalFilename.substring(originalFilename.indexOf(".")+1);
    }

    private File RenameFile(File oldFile, String oriFileName){
        File oldfile =new File(oldFile.toURI());
        File newfile =new File(oriFileName);

        if(oldfile.renameTo(newfile)){
            System.out.println("Rename succesful");
            return newfile;
        }else{
            System.out.println("Rename failed");
            return  oldFile;
        }

    }
}
