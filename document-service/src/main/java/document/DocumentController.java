package document;

import document.docu.PostDocument.PostDocResource;
import document.docu.PostDocument.PostDocStatus;
import document.docu.documentResult.DocumentResult;
import document.uploadfile.StorageFileNotFoundException;
import document.uploadfile.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jongzazaal on 18/3/2560.
 */

@CrossOrigin
@Controller
@RestController
public class DocumentController {


    private final DocumentRepository documentRepository;
    private final StorageService storageService;

    @Autowired
    public DocumentController(DocumentRepository documentRepository, StorageService storageService){
        this.documentRepository = documentRepository;
        this.storageService = storageService;
    }




    @GetMapping("/document")
    public @ResponseBody ResponseEntity<List<DocumentResult>> getAllDocument(
            @RequestParam(value="user_id", defaultValue="0") String user_id){

        if(documentRepository.isUser(user_id)){
            return new ResponseEntity<>(documentRepository.allDocumentById(Integer.parseInt(user_id)), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(new ArrayList<DocumentResult>(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/document")
    public @ResponseBody ResponseEntity<PostDocStatus> postUserInfo(@RequestBody PostDocResource postDocResource){

                PostDocStatus ps = documentRepository.createDoc(postDocResource);
                if(ps.isResponse()){
                    return new ResponseEntity<PostDocStatus>(ps, HttpStatus.FOUND);
                }
        return new ResponseEntity<PostDocStatus>(ps, HttpStatus.NO_CONTENT);
//        if(authRepository.isUser(userPass)){
//            WhoIsUserResult whoIsUserResult =  authRepository.whoIsUser(userPass.getUser_username(),
//                    userPass.getUser_password());
//            return new ResponseEntity<>(whoIsUserResult, HttpStatus.FOUND);
//        }
//        return new ResponseEntity<>(new WhoIsUserResult(), HttpStatus.NO_CONTENT);
    }


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(DocumentController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        Stream<Path> file = storageService.loadAll();
        file.forEach(System.out::println);
        System.out.println(model);
        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] mfile,
                                   RedirectAttributes redirectAttributes) {

        for(MultipartFile file:mfile) {

            storageService.store(file);
//            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        }
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



}
