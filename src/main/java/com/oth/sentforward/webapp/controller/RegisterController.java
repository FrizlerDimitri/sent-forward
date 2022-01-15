package com.oth.sentforward.webapp.controller;


import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.persistence.entities.Address;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;
import com.oth.sentforward.webapp.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "register")
    public String register(Model model) {


        model.addAttribute("registerForm", new RegisterDto());
        return "register-account";

    }

    @RequestMapping(value = "/register/pw-error")
    public String pwError(Model model) {

        model.addAttribute("registerForm", new RegisterDto());
        model.addAttribute("pwError", true);
        return "register-account";

    }

    @RequestMapping(value = "/register/ac-error")
    public String accountError(Model model) {


        model.addAttribute("registerForm", new RegisterDto());
        model.addAttribute("acError", true);
        return "register-account";

    }



    @RequestMapping(value = "/registrationSubmit", method = RequestMethod.POST)
    public String registrationSubmit(@ModelAttribute("registerForm") RegisterDto registerDto) {


        String accountName = registerDto.getAccountName();

        if (accountService.getMasterAccountByAccountname(accountName).isPresent()) {
            return "redirect:/register/ac-error";
        }


        String pw = registerDto.getPassword();
        String pwConfirm = registerDto.getPasswordConfirm();

        //TODO create Exception and Handling
        if (!pw.equals(pwConfirm)) {
            return "redirect:/register/pw-error";
        }

        //create user
        String country = registerDto.getCountry();
        String street = registerDto.getStreet();
        String town = registerDto.getTown();

        Address address = new Address(country, street, town);

        String lastName = registerDto.getLastName();
        String name = registerDto.getName();

        UserEntity user = new UserEntity(lastName, name, address);

        //create masterAccount
        MasterAccount masterAccount = new MasterAccount(accountName, pw, user);
        accountService.createMasterAccount(masterAccount);

        return "redirect:/login";
    }


}
