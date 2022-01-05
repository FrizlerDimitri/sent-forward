package com.oth.sentforward.webapp.controller;

import com.oth.sentforward.Utils.SentForwardUtilsConfig;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.bussnislogic.services.EmailService;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.entities.SentEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class MailController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentForwardUtilsConfig utilsConfig;

    @RequestMapping(value="/account/sent/email/{emailId}")
    public String getSentEmailDetail(Model model, @PathVariable("emailId") Long emailId, Principal principal)
    {
        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<SentEmail> optionalSentEmail=emailService.getSentEmailByID(emailId);
        optionalSentEmail.ifPresent( email -> {
            model.addAttribute("email", email);
            model.addAttribute("sdf", utilsConfig.getSDF());

            List<String> toList = new ArrayList<>();
            email.getTo().forEach( EmailAccount -> {
                toList.add(EmailAccount.getEmailAddress());
            }  );
            model.addAttribute("toList",String.join(", ", toList));

        } );


        return "sentEmailDetail";
    }



}
