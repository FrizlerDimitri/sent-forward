package com.oth.sentforward.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @RequestMapping(value="/login")
    public String login()
    {
        return "login";
    }


    @RequestMapping(value="/login-Error")
    public String loginError(Model model)
    {
        model.addAttribute("loginError", true);
        return "login";
    }



//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String loginSubmit()
//    {
//        return "account";
//    }


}
