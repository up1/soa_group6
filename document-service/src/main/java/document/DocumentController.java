package document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody ResponseEntity<WhoIsUserResult> postUserInfo(@RequestBody UserPassResult userPass){

        if(authRepository.isUser(userPass)){
            WhoIsUserResult whoIsUserResult =  authRepository.whoIsUser(userPass.getUser_username(),
                    userPass.getUser_password());
            return new ResponseEntity<>(whoIsUserResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new WhoIsUserResult(), HttpStatus.NO_CONTENT);
    }
}
