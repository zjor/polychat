package cz.zjor.toys.polychat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:enter";
    }

    @RequestMapping(value = "/{path:enter|chat}", method = RequestMethod.GET)
    public String path(@PathVariable("path") String path) {
        return path;
    }


}
