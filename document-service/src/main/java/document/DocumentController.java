package document;

import document.docu.DocumentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jongzazaal on 18/3/2560.
 */

@CrossOrigin
@Controller
@RestController
public class DocumentController {


    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentController(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @GetMapping("/document")
    public @ResponseBody ResponseEntity<List<DocumentResult>> getAllDocument(
            @RequestParam(value="user_id", defaultValue="0") String user_id){

        return new ResponseEntity<>(documentRepository.allDocumentById(Integer.parseInt(user_id)), HttpStatus.FOUND);
//        documentRepository.allDocumentById(user_id);

//        if(authRepository.isUser(userPass)){
//            WhoIsUserResult whoIsUserResult =  authRepository.whoIsUser(userPass.getUser_username(),
//                    userPass.getUser_password());
//            return new ResponseEntity<>(whoIsUserResult, HttpStatus.FOUND);
//        }
//        return new ResponseEntity<>(new WhoIsUserResult(), HttpStatus.NO_CONTENT);
    }
}
