package soa.group6.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sekcg on 3/6/2017.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(ModelAndView modelAndView) {
        return "login";
    }

}
