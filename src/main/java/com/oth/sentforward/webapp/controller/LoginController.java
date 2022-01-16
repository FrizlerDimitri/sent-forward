package com.oth.sentforward.webapp.controller;


import com.oth.sentforward.security.SentForwardSecurityConfiguration;
import com.oth.sentforward.security.SentForwardSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @Autowired
    SentForwardSecurityUtils securityUtils;

    @RequestMapping(value="/login")
    public String login()
    {
        if (securityUtils.isAuthenticated())
        {
            return "redirect:/account";
        }

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
