package org.wapache.openapi.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to api documentation
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:index.html";
    }

}