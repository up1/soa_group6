package com.teamsmokeweed;

import com.teamsmokeweed.files.FileSystemStorageService;
import com.teamsmokeweed.files.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 22/4/2560.
 */
@Repository
public class FilesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    FileSystemStorageService fileSystemStorageService;

    public FilesRepository(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }

    public void UploadFile(MultipartFile file, int doc_id, int file_upload_id, int file_upload_revision) throws IOException {
        StorageProperties storageProperties = new StorageProperties();
        storageProperties.setLocation("upload/"+doc_id+"/"+file_upload_id+"/"+file_upload_revision+"/");
        fileSystemStorageService = new FileSystemStorageService(storageProperties);
        this.fileSystemStorageService.init();
//        try {
//            this.fileSystemStorageService.init();
//        }
//        catch (Exception e){
//
//        }
        this.fileSystemStorageService.store(file);
    }

    //newDocument, oldDocument newFile
    public Map<String, Object> UploadFileToDB(String fileName, long fileSize, int doc_id){

        jdbcTemplate.update("INSERT INTO file_upload(doc_id, file_upload_revision, file_name, file_size) VALUES (?, ?, ?, ?);",
                new Object[]{ doc_id, 1, fileName, fileSize});

        Map<String, Object> response = this.jdbcTemplate.queryForMap("SELECT file_upload_id, doc_id, MAX(file_upload_revision) AS file_upload_revision FROM file_upload WHERE doc_id = ? AND file_name = ? GROUP BY file_upload_id",
                new Object[]{ doc_id, fileName});
        return response;
//        return (Integer) response.get("file_upload_id");
    }
    //oldDocument oleFile
    public Map<String, Object> UploadFileToDB(String fileName, long fileSize, int doc_id, int file_upload_id){

//        GetFileInfoResponse response = jdbcTemplate.queryForObject("", new Object[]{}, GetFileInfoResponse.class)
        jdbcTemplate.update("INSERT INTO file_upload(file_upload_id, doc_id, file_upload_revision, file_name, file_size) VALUES ( ?, ?, (SELECT MAX(a.file_upload_revision)+1 AS file_upload_revision FROM file_upload a WHERE a.file_upload_id = ? AND a.doc_id = ? GROUP BY a.file_upload_id), ?, ?)",
                new Object[]{file_upload_id, doc_id, file_upload_id, doc_id, fileName, fileSize});

        Map<String, Object> response = this.jdbcTemplate.queryForMap("SELECT file_upload_id, doc_id, MAX(file_upload_revision) AS file_upload_revision FROM file_upload WHERE doc_id = ? AND file_name = ? GROUP BY file_upload_id",
                new Object[]{ doc_id, fileName});
        return response;
    }


    public ResponseEntity<Resource> DownloadFile(int doc_id, int file_upload_id, int file_upload_revision, String filename) throws MalformedURLException {
        StorageProperties storageProperties = new StorageProperties();
        storageProperties.setLocation("upload/"+doc_id+"/"+file_upload_id+"/"+file_upload_revision+"/");
        this.fileSystemStorageService = new FileSystemStorageService(storageProperties);
        Resource file = this.fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    public List<Map<String, Object>> GetFileInfo(int doc_id){

//        List<GetFileInfoResponse> list = jdbcTemplate.query("SELECT file_upload_id, doc_id, file_upload_revision, file_name, file_upload_date, file_size FROM file_upload WHERE doc_id = ? GROUP BY file_upload_id DESC",
//                new Object[]{doc_id}, new GetFileInfoRowMapper());
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT file_name AS name, file_size AS 'size', file_upload_date AS 'time', file_upload_revision AS revision , file_upload_id AS id FROM file_upload WHERE doc_id = ? GROUP BY file_upload_id DESC",
                new Object[]{doc_id});
        return result;
    }

    public Map<String, Object> isNewFile(int doc_id, String fileName){
        Map<String, Object> result = null;
        try{
        result = this.jdbcTemplate.queryForMap("SELECT file_upload_id, doc_id FROM file_upload WHERE doc_id = ? AND file_name = ? GROUP BY file_name",
                new Object[]{doc_id, fileName});
            System.out.println("fond");
        }catch (Exception e){
            result = new HashMap<>();
            System.out.println("notfound");
        }
        return result;
    }
}
