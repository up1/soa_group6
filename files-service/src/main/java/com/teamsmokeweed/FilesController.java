package com.teamsmokeweed;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jongzazaal on 22/4/2560.
 */
@CrossOrigin
@Controller
@RestController
public class FilesController {
    private final FilesRepository filesRepository;

    public FilesController(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @PostMapping(value = "/upload")
    public void upladFile(@RequestParam("file") MultipartFile mfile,
                          RedirectAttributes redirectAttributes,
                          @RequestParam(value = "doc_id", defaultValue = "0") int doc_id
//                          @RequestParam(value = "file_upload_id", defaultValue = "0") int file_upload_id,
//                          @RequestParam(value = "file_upload_revision", defaultValue = "0") int file_upload_revision
    ) throws IOException {
//        if(file_upload_revision == 1) {
//            file_upload_id = this.filesRepository.uploadFileToDB(mfile, doc_id);
//        }
//        else {
//
//        }

        Map<String, Object> result = this.filesRepository.isNewFile(doc_id, mfile.getOriginalFilename());
        Map<String, Object> resultUploadFile = null;
        //upload newrevision
        if(!result.isEmpty()){
//          Uploadnewfile
            resultUploadFile = this.filesRepository.uploadFileToDB(mfile.getOriginalFilename(), mfile.getSize(), doc_id,
                    (Integer) result.get("file_upload_id"));
        }
        //uploadnewfile
        else {
//          Insert Into DB
            resultUploadFile = this.filesRepository.uploadFileToDB(mfile.getOriginalFilename(), mfile.getSize(), doc_id);
        }

        //UploadFileToserver
        this.filesRepository.uploadFile(mfile, (Integer) resultUploadFile.get("doc_id"),
                (Integer) resultUploadFile.get("file_upload_id"),
                (Integer) resultUploadFile.get("file_upload_revision")
                );

//        this.filesRepository.uploadFile(mfile, doc_id, file_upload_id,1);
    }

    @GetMapping(value = "/documents/{docid}/files")
//    @GetMapping(value = "/download/{docid}/{fileid}/{revision}/{filename:.+}")
//    /documents/:id/files?filename={filename}&revision={fileRevision}&id={fileId}
    public ResponseEntity<Resource> downloadFile(@PathVariable int docid,
                                                 @RequestParam(value = "filename") String filename,
                                                 @RequestParam(value = "revision") int revision,
                                                 @RequestParam(value = "id") int fileid


    ) throws MalformedURLException {
        return this.filesRepository.downloadFile(docid, fileid, revision, filename);
    }

    @GetMapping(value = "/files")
    public List<Map<String, Object>> getFileInfo(@RequestParam int doc_id){
        return this.filesRepository.getFileInfo(doc_id);
    }

//    @DeleteMapping(value = "")


}
