package com.oth.sentforward.webapp.restcontroller;


import com.oth.sentforward.bussnislogic.exeption.CanNotSentException;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.IEmailService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.SentEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class EmailRestController {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/sentforward-rest-api/sentEmails", method = RequestMethod.POST)
    public ResponseEntity sentEmailApi(@RequestBody SentEmail sentEmail) {

        try {
            return new ResponseEntity <SentEmail>(emailService.sentEmail(sentEmail).get(), HttpStatus.OK);

        }catch (CanNotSentException e)
        {
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }
}
