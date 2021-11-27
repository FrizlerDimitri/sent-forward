package com.oth.sentforward.webapp.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("session")
public class TestController {


    @RequestMapping(value="/")
    public String test()
    {
        return "starter";
    }

}
