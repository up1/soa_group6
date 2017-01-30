package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RestController
public class UserController {

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @CrossOrigin(origins = "http://localhost:9001", maxAge = 3600)
    @RequestMapping(value = "/user")
    public User getUser(@RequestParam(value="id", defaultValue="3") int id) {
        return this.userRepository.findById((long) id);
//        return new User(1);  nn
    }

    @RequestMapping("/user2")
    public List<User> getAllUser(@RequestParam(value="page", defaultValue="1") int page,
                                 @RequestParam(value="item_per_page",defaultValue = "5") int limit) {

        return this.userRepository.findAllLimit(page, limit);
//        return new User(1);
//        return "aa";
    }

    @RequestMapping("/getPageAll")
    public int getPageAll(@RequestParam(value="item_per_page",defaultValue = "5") int limit){


        return this.userRepository.getPageAll(limit);
    }



}
