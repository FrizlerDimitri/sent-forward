package com.oth.sentforward.webapp.controller;


import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entities.Address;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.UserEntity;
import com.oth.sentforward.webapp.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {


    private final String REGISTER="register";



    @Autowired
    private IAccountService accountService;


    @RequestMapping(value = "register")
    public String register(Model model) {

        model.addAttribute("registerForm", new RegisterForm());
        return "register-account";

    }


    @RequestMapping(value = "/registrationSubmit", method = RequestMethod.POST)
    public String registrationSubmit
            (
                    @ModelAttribute("registerForm") RegisterForm registerForm
            ) throws Exception {


        String accountName = registerForm.getAccountName();
        String pw=registerForm.getPassword();
        String pwConfirm=registerForm.getPasswordConfirm();

        //TODO create Exception and Handling
        if(!pw.equals(pwConfirm))
        {
            throw new Exception("passwords are different");
        }

        //create user
        String country=registerForm.getCountry();
        String street=registerForm.getStreet();
        String town=registerForm.getTown();

        Address address = new Address(country,street,town);

        String lastName = registerForm.getLastName();
        String name = registerForm.getName();

        UserEntity user = new UserEntity(lastName,name,address);

        //create masterAccount
        MasterAccount masterAccount = new MasterAccount(accountName, pw, user);
        accountService.createMasterAccount(masterAccount);

        return "redirect:/login";
    }


}
