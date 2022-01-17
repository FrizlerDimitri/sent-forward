package com.oth.sentforward.webapp.controller;

import com.oth.sentforward.Utils.SentForwardUtilsConfig;
import com.oth.sentforward.bussnislogic.exeption.CanNotSentException;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.bussnislogic.services.EmailService;
import com.oth.sentforward.persistence.entities.*;
import com.oth.sentforward.webapp.dto.SendEmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class MailController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentForwardUtilsConfig utilsConfig;

    //Sent E-mails

    @RequestMapping(value = "/account/sent/{emailId}")
    public String getSentEmail(Model model, @PathVariable("emailId") Long emailAddressId, Principal principal) {
        // For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        // For Content
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setId(emailAddressId);
        Optional<EmailAccount> optionalEmailAccount = accountService.loadEmailAccount(emailAccount);
        optionalEmailAccount.ifPresent(eAcc -> {
            model.addAttribute("emailAccount", eAcc);
            model.addAttribute("sdf", utilsConfig.getSDF());


            List<SentEmail> sortedList = (List<SentEmail>) eAcc.getSentEmails();
            sortedList.sort((email1, email2) -> {
                if (email1.getSentDate().before(email2.getSentDate())) {
                    return 1;
                } else {
                    return -1;
                }
            });

            model.addAttribute("sortedEmails", sortedList);

        });

        return "sentEmail";
    }

    @RequestMapping(value = "/account/sent/email/{emailId}")
    public String getSentEmailDetail(Model model, @PathVariable("emailId") Long emailId, Principal principal) {
        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<SentEmail> optionalSentEmail = emailService.getSentEmailByID(emailId);
        optionalSentEmail.ifPresent(email -> {
            model.addAttribute("email", email);
            model.addAttribute("sdf", utilsConfig.getSDF());

            List<String> toList = new ArrayList<>();
            email.getTo().forEach(EmailAccount -> toList.add(EmailAccount.getEmailAddress()));
            model.addAttribute("toList", String.join(", ", toList));

        });

        return "sentEmailDetail";
    }


    //Received E-Mails

    @RequestMapping(value = "/account/inbox/{emailId}")
    public String getReceivedEmail(Model model, @PathVariable("emailId") Long emailAddressId, Principal principal) {
        // For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        // For Content
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setId(emailAddressId);
        Optional<EmailAccount> optionalEmailAccount = accountService.loadEmailAccount(emailAccount);
        optionalEmailAccount.ifPresent(eAcc -> {
            model.addAttribute("emailAccount", eAcc);
            model.addAttribute("sdf", utilsConfig.getSDF());


            List<ReceivedEmail> sortedList = (List<ReceivedEmail>) eAcc.getReceivedEmails();
            sortedList.sort((email1, email2) -> {
                if (email1.getReceivedDate().before(email2.getReceivedDate())) {
                    return 1;
                } else {
                    return -1;
                }
            });
            model.addAttribute("sortedEmails", sortedList);
        });

        return "ReceivedEmail";
    }

    @RequestMapping(value = "/account/inbox/email/{emailId}")
    public String getReceivedEmailDetail(Model model, @PathVariable("emailId") Long emailId, Principal principal) {
        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<ReceivedEmail> optionalSentEmail = emailService.getReceivedEmailById(emailId);
        optionalSentEmail.ifPresent(email -> {
            model.addAttribute("email", email);
            model.addAttribute("sdf", utilsConfig.getSDF());

            List<String> toList = new ArrayList<>();
            email.getTo().forEach(EmailAccount -> toList.add(EmailAccount.getEmailAddress()));
            model.addAttribute("toList", String.join(", ", toList));

        });

        return "receivedEmailDetail";
    }


    //Saved E-Mails

    @RequestMapping(value = "account/saved/{emailAddressId}")
    public String getSavedEmail(Model model, @PathVariable Long emailAddressId, Principal principal)
    {
        // For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        // For Content
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setId(emailAddressId);
        Optional<EmailAccount> optionalEmailAccount = accountService.loadEmailAccount(emailAccount);
        optionalEmailAccount.ifPresent(eAcc -> {
            model.addAttribute("emailAccount", eAcc);
            model.addAttribute("sdf", utilsConfig.getSDF());

            List<SavedEmail> sortedList = (List<SavedEmail>) eAcc.getSavedEmails();
            sortedList.sort((email1, email2) -> {
                if (email1.getLastModified().before(email2.getLastModified())) {
                    return 1;
                } else {
                    return -1;
                }
            });
            model.addAttribute("sortedEmails", sortedList);

        });

        return "SavedEmail";
    }

    @RequestMapping(value = "account/saved/{emailAddressId}", method = RequestMethod.POST)
    public String saveEmailPost(@PathVariable Long emailAddressId, @ModelAttribute SendEmailDto sendEmailDto, @RequestParam(required = false) Long oldId)
    {

        String to= sendEmailDto.getTo();
        to=to.replaceAll(";|,"," ");
        to=to.replaceAll("\\s+"," ");
        String[] stringArr = to.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArr));

        String subject = sendEmailDto.getSubject();
        String content = sendEmailDto.getContent();

        SavedEmail saveEmail=new SavedEmail();

        if(oldId != null)
        {
            if(emailService.getSavedEmailById(oldId).isPresent())
            {
                saveEmail=emailService.getSavedEmailById(oldId).get();
            }

        }

        saveEmail.setContent(content);
        saveEmail.setSubject(subject);
        saveEmail.setLastModified(new Date());

        List <String> canNotSentTo = new ArrayList<>();
        for ( String email : stringList )
        {
            Optional<EmailAccount> optionalEmailAccount = accountService.getEmailAccountByEmailAddress(email);
            if (optionalEmailAccount.isPresent())
            {
                saveEmail.getTo().add(optionalEmailAccount.get());
            }else {
                canNotSentTo.add(email);
            }

        }

        EmailAccount from =  accountService.getEmailAccountById(emailAddressId).orElse(null);
        saveEmail.setFrom(from);



        emailService.saveEmail(saveEmail);

        return "redirect:/account/saved/"+emailAddressId;
    }

    @RequestMapping(value = "/account/saved/email/{emailId}")
    public String getSavedEmailDetail(Model model, @PathVariable("emailId") Long emailId, Principal principal)
    {

        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });


        model.addAttribute("sentEmailDto", new SendEmailDto());
        // For Content
        emailService.getSavedEmailById(emailId).ifPresent( savedEmail ->{

            model.addAttribute( "email",savedEmail);

            SendEmailDto sendEmailDto = new SendEmailDto();
            sendEmailDto.setContent(savedEmail.getContent());
            sendEmailDto.setSubject(savedEmail.getSubject());
            model.addAttribute("emailDTO",sendEmailDto);

        } );
        return "SavedEmailDetail";
    }





    //Create E-Mail

    @RequestMapping(value = "/account/{emailAddressId}/new")
    public String sendEmail(Model model, Principal principal, @PathVariable Long emailAddressId)
    {
        //For Sidebar
        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });


        model.addAttribute("sentEmailDto", new SendEmailDto());
        // For Content
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setId(emailAddressId);
        Optional<EmailAccount> optionalEmailAccount = accountService.loadEmailAccount(emailAccount);
        optionalEmailAccount.ifPresent(eAcc -> {

            model.addAttribute("emailAccount", eAcc);

        });

        return "newEmail";
    }

    @RequestMapping(value = "/account/{emailAddressId}/new", method = RequestMethod.POST)
    public String sendEmailPost(@PathVariable Long emailAddressId, @ModelAttribute SendEmailDto sendEmailDto)
    {
        String to= sendEmailDto.getTo();
        to=to.replaceAll(";|,"," ");
        to=to.replaceAll("\\s+"," ");
        String[] stringArr = to.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArr));

        String subject = sendEmailDto.getSubject();
        String content = sendEmailDto.getContent();

        SentEmail sentEmail=new SentEmail();
        sentEmail.setContent(content);
        sentEmail.setSubject(subject);


        EmailAccount from =  accountService.getEmailAccountById(emailAddressId).orElse(null);
        sentEmail.setFrom(from);


        List <String> canNotSentTo = new ArrayList<>();
        for ( String email : stringList )
        {
            Optional<EmailAccount> optionalEmailAccount = accountService.getEmailAccountByEmailAddress(email);
            if (optionalEmailAccount.isPresent())
            {
                sentEmail.getTo().add(optionalEmailAccount.get());
            }else {
                canNotSentTo.add(email);
            }

        }

        try {

            emailService.sentEmail(sentEmail);

        } catch (CanNotSentException e) {
            e.printStackTrace();
        }

        return "redirect:/account/sent/"+emailAddressId;
    }

}
