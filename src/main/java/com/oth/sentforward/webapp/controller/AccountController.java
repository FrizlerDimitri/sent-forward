package com.oth.sentforward.webapp.controller;


import com.oth.sentforward.Utils.SentForwardUtilsConfig;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
public class AccountController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private SentForwardUtilsConfig utilsConfig;

    @RequestMapping(value="/account")
    public String account(Model model, Principal principal)
    {
        model.addAttribute("emailAccount", new EmailAccount());
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


        model.addAttribute("emailAccount", new EmailAccount());
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        return "basic/account-layout";
    }



    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String creatEmailAccount(
            @ModelAttribute("emailAccount")EmailAccount emailAccount,
            Principal principal

    )
    {
        System.out.println(emailAccount.getEmailAddress());
        System.out.println(emailAccount.getEmailPw());
        System.out.println(principal.getName());


        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());

        //Testing
        if (optionalMasterAccount.isPresent())
        {
            MasterAccount masterAccount=optionalMasterAccount.get();
            masterAccount.getEmailAccounts().add(emailAccount);
            emailAccount.setMasterAccount(masterAccount);
            accountService.updateMasterAccount(masterAccount);

        }else{
            System.out.println("can not found");
        }

        return "redirect:/account";
    }



    @RequestMapping(value="/account/calendar/{emailAddressId}")
    public String getCalendar(Model model, @PathVariable("emailAddressId") Long emailAddressId, Principal principal)
    {
//        EmailAccount emailAccount=new EmailAccount();
//        emailAccount.setId(emailAddressId);
//        Optional<EmailAccount> optionalEmailAccount= accountService.loadEmailAccount(emailAccount);
//        optionalEmailAccount.ifPresent( eAcc -> {
//            model.addAttribute("emailAccount",eAcc);
//            model.addAttribute("Calendar", eAcc.getCalendar());
//        });

        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        // model.addAttribute()
        return "calendar";
    }


    @RequestMapping(value="/account/sent/{emailAddressId}")
    public String getSent(Model model, @PathVariable("emailAddressId") Long emailAddressId, Principal principal)
    {
        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });


        // For Content
        EmailAccount emailAccount=new EmailAccount();
        emailAccount.setId(emailAddressId);
        Optional<EmailAccount> optionalEmailAccount= accountService.loadEmailAccount(emailAccount);
        optionalEmailAccount.ifPresent( eAcc -> {
            model.addAttribute("emailAccount",eAcc);
            //SimpleDateFormat sdf= new SimpleDateFormat("MM.dd.yyyy, hh:mm");
            model.addAttribute("sdf", utilsConfig.getSDF());

        });

        // model.addAttribute()
        return "sentEmail";
    }







}
