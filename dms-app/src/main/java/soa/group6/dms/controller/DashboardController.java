package soa.group6.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sekcg on 3/7/2017.
 */
@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboard(ModelAndView modelAndView) {
        modelAndView.addObject("page", "dashboard");
        return "index";
    }

}
