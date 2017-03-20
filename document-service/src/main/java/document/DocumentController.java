package document;

import com.google.gson.Gson;
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
import org.springframework.mock.web.MockMultipartFile;
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
import java.util.UUID;
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
    public DocumentController(DocumentRepository documentRepository, StorageService storageService) {
        this.documentRepository = documentRepository;
        this.storageService = storageService;
    }


    @GetMapping("/document")
    public
    @ResponseBody
    ResponseEntity<List<DocumentResult>> getAllDocument(
            @RequestParam(value = "user_id", defaultValue = "0") String user_id) {

        if (documentRepository.isUser(user_id)) {
            return new ResponseEntity<>(documentRepository.allDocumentById(Integer.parseInt(user_id)), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(new ArrayList<DocumentResult>(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/document")
    public
    @ResponseBody
    ResponseEntity<PostDocStatus> postUserInfo(@RequestParam("file") MultipartFile[] mfile,
                                               RedirectAttributes redirectAttributes,
                                               @RequestParam("info") String s) throws IOException {

        //upload File
        List<String> linkload = new ArrayList<>();
        for (MultipartFile file : mfile) {
            //rename file
            int lastIndex = file.getOriginalFilename().lastIndexOf('.');
            String oriFilename = UUID.randomUUID().toString() + file.getOriginalFilename().substring(lastIndex, file.getOriginalFilename().length());
            MultipartFile xFile = new MockMultipartFile(file.getOriginalFilename(), oriFilename, file.getContentType(), file.getBytes());
            //upload file
            storageService.store(xFile);
            linkload.add(oriFilename);
        }

        System.out.println(storageService.loadAll());

        //string json to POJO
        PostDocResource postDocResource = new Gson().fromJson(s, PostDocResource.class);

        //createDoc
        PostDocStatus ps = documentRepository.createDoc(postDocResource, linkload);
        if (ps.isResponse()) {
            return new ResponseEntity<PostDocStatus>(ps, HttpStatus.FOUND);
        }
        return new ResponseEntity<PostDocStatus>(ps, HttpStatus.NO_CONTENT);
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] mfile,
                                   RedirectAttributes redirectAttributes, @RequestParam("info") String s) throws IOException {


        PostDocResource ps = new Gson().fromJson(s, PostDocResource.class);
        System.out.println(ps.getDoc_title());
        for (MultipartFile file : mfile) {

            System.out.println(file.getOriginalFilename().lastIndexOf('.'));
            int lastIndex = file.getOriginalFilename().lastIndexOf('.');
            MultipartFile xFile = new MockMultipartFile("name", UUID.randomUUID().toString() + file.getOriginalFilename().substring(lastIndex, file.getOriginalFilename().length()), file.getContentType(), file.getBytes());
            storageService.store(xFile);
//            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        }
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/t")
    public
    @ResponseBody
    ResponseEntity<PostDocStatus> Testre(@RequestBody PostDocResource postDocResource) {


        return new ResponseEntity<PostDocStatus>(documentRepository.intt(postDocResource), HttpStatus.FOUND);
    }

}
