package com.oth.sentforward.webapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SentForwardErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/login";
    }

}
