package com.oth.sentforward.webapp.controller;


import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.webapp.dto.NewAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AccountController {


    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/account")
    public String account(Model model, Principal principal)
    {
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        return "account";
    }

    @RequestMapping(value="/test")
    public String accountLayout(Model model, Principal principal)
    {
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        return "basic/account-layout";
    }


    @RequestMapping(value = "account/newEmail")
    public String createNewEmail(Model model, Principal principal)
    {

        model.addAttribute("newAddressDto", new NewAddressDto());
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        return "createNewAddress";
    }

    @RequestMapping(value = "account/newEmail", method = RequestMethod.POST)
    public String createNewEmailPost(Model model, Principal principal, NewAddressDto newAddressDto)
    {
        String emailAddress = newAddressDto.getEmailAddress()+"@sentforward.de";

        //TODO if the account exist already -> error

        EmailAccount newEmailAccount= new EmailAccount();
        newEmailAccount.setEmailAddress(emailAddress);


        Optional<MasterAccount> optionalMasterAccount= accountService.getMasterAccountByAccountname(principal.getName());
        optionalMasterAccount.ifPresent( acc -> {

            newEmailAccount.setMasterAccount(acc);
            acc.getEmailAccounts().add(newEmailAccount);
            accountService.updateMasterAccount(acc);

        });


        return "redirect:/account";
    }



    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String creatEmailAccount(
            @ModelAttribute("emailAccount")EmailAccount emailAccount,
            Principal principal

    )
    {

        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());

        //Testing
        if (optionalMasterAccount.isPresent())
        {
            MasterAccount masterAccount=optionalMasterAccount.get();
            masterAccount.getEmailAccounts().add(emailAccount);
            emailAccount.setMasterAccount(masterAccount);
            accountService.updateMasterAccount(masterAccount);

        }

        return "redirect:/account";
    }














}
