package demo;

import demo.config.Tes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/user")
    public User getUser(@RequestParam(value="id", defaultValue="3") int id) {
        return this.userRepository.findById((long) id);

    }

    @RequestMapping("/user2")
    public List<User> getAllUser(@RequestParam(value="page", defaultValue="1") int page,
                                 @RequestParam(value="item_per_page",defaultValue = "5") int limit) {

        return this.userRepository.findAllLimit(page, limit);

    }

    @RequestMapping("/getPageAll")
    public int getPageAll(@RequestParam(value="item_per_page",defaultValue = "5") int limit){


        return this.userRepository.getPageAll(limit);
    }


    @RequestMapping(value="/sss", method = RequestMethod.POST)
    public  @ResponseBody String  sampletext(@RequestBody Tes tes2) {
        System.out.println(tes2.getItem_per_page());
        return "{\"item_per_page\":"+ tes2.getItem_per_page() +"}";
    }




}
