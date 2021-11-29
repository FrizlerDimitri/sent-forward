package com.oth.sentforward.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class accountController {

    @RequestMapping(value="/account")
    public String account()
    {
        return "account";
    }

}
