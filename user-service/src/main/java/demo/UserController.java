package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/user")
    public User getUser(@RequestParam(value="id", defaultValue="1") int id) {
        return this.userRepository.findById((long) id);
//        return new User(1);  nn
    }

    @RequestMapping("/user2")
    public List<User> getAllUser(@RequestParam(value="page", defaultValue="1") int page,
                                 @RequestParam(value="item_per_page",defaultValue = "5") int id) {

//        List<String> response = new ArrayList<String>();
//        for (User i:   this.userRepository.findAllLimit((long) id)) {
//            response.add(i.toString());
//        }
        return this.userRepository.findAllLimit((long) id);
//        return new User(1);
//        return "aa";
    }
}
