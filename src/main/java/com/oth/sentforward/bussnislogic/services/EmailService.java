package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.exeption.CanNotSentException;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.IEmailService;
import com.oth.sentforward.persistence.entities.*;
import com.oth.sentforward.persistence.repositories.IReceivedEmailRepository;
import com.oth.sentforward.persistence.repositories.ISavedEmailRepository;
import com.oth.sentforward.persistence.repositories.ISentEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmailService implements IEmailService {


    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISentEmailRepository sentEmailRepository;

    @Autowired
    private IReceivedEmailRepository receivedEmailRepository;

    @Autowired
    private ISavedEmailRepository savedEmailRepository;


    public EmailService(ISentEmailRepository sentEmailRepository) {
        this.sentEmailRepository = sentEmailRepository;
    }


    @Override
    public Optional<SentEmail> getSentEmailByID(Long id) {
        return sentEmailRepository.findById(id);
    }

    @Override
    public Optional<ReceivedEmail> getReceivedEmailById(Long id)
    {
        return receivedEmailRepository.findById(id);
    }

    @Override
    public Optional<SavedEmail> getSavedEmailById(Long id) {
        return savedEmailRepository.findById(id);
    }

    @Override
    public Optional<SentEmail> sentEmail(SentEmail email) throws CanNotSentException {

        List<EmailAccount> tempTo = (ArrayList<EmailAccount>) email.getTo();
        EmailAccount tempFrom = email.getFrom();


        Optional<EmailAccount> optionalFrom = accountService.getEmailAccountByEmailAddress(tempFrom.getEmailAddress());
        EmailAccount from = optionalFrom.orElseThrow(() -> new CanNotSentException("E-Mail Address " + tempFrom.getEmailAddress()+" not found"));

        List<EmailAccount> to = new ArrayList<>();
        for ( EmailAccount emailAccount : tempTo )
        {
            Optional<EmailAccount> toEmailAccount= accountService.getEmailAccountByEmailAddress(emailAccount.getEmailAddress());
            if(toEmailAccount.isPresent())
            {
                to.add(toEmailAccount.get());
            }
        }

        SentEmail sEmail  = new SentEmail();
        sEmail.setFrom(from);
        sEmail.setSubject(email.getSubject());
        sEmail.setContent(email.getContent());
        sEmail.setSentDate(new Date());
        sEmail.getTo().addAll(to);

        from.getSentEmails().add(sEmail);
        accountService.updateEmailAccount(from);

         for (EmailAccount emailAccount : to)
         {
             ReceivedEmail receivedEmail = new ReceivedEmail();
             receivedEmail.setFrom(from);
             receivedEmail.setSubject(email.getSubject());
             receivedEmail.setContent(email.getContent());
             receivedEmail.setReceivedDate(new Date());
             receivedEmail.getTo().addAll(to);
             emailAccount.getReceivedEmails().add(receivedEmail);

         }

         accountService.updateAllEmailAccount(to);

        return Optional.of(sEmail);
    }




}
