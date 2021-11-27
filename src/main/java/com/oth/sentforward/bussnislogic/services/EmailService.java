package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.persistence.repositories.IEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailService {


    @Autowired
    private IEmailRepository iEmailRepository;

    public EmailService(IEmailRepository iEmailRepository) {
        this.iEmailRepository = iEmailRepository;
    }
}
