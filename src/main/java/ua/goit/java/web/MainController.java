package ua.goit.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminIndex(){

        return "admin/index";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @RequestMapping(value = "/restaurantScheme", method = RequestMethod.GET)
    public ModelAndView scheme() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("scheme");

        return modelAndView;
    }

}
