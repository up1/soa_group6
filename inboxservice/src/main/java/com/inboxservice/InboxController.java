package com.inboxservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 20/3/2560.
 */
@CrossOrigin
@Controller
@RestController
public class InboxController {

    private final InboxRepository inboxRepository;

    @Autowired
    public InboxController(InboxRepository inboxRepository) {
        this.inboxRepository = inboxRepository;
    }

    @RequestMapping("/inbox")
    public
    @ResponseBody
    ResponseEntity<List<Inbox>> getDepartmentInbox (@RequestParam(value="dep_id", defaultValue = "1") int dep_id){

        List<Inbox> dep_inbox = this.inboxRepository.getDepartmentInbox(dep_id);
        if (dep_inbox.size() > 0){
            return new ResponseEntity<>(dep_inbox, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new ArrayList<Inbox>(), HttpStatus.NO_CONTENT);

    }

}
