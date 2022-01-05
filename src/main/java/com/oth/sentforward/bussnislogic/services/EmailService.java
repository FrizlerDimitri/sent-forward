package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.IEmailService;
import com.oth.sentforward.persistence.entities.SentEmail;
import com.oth.sentforward.persistence.repositories.ISentEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService implements IEmailService {


    @Autowired
    private ISentEmailRepository sentEmailRepository;

    public EmailService(ISentEmailRepository sentEmailRepository) {
        this.sentEmailRepository = sentEmailRepository;
    }


    @Override
    public Optional<SentEmail> getSentEmailByID(Long id) {

        return sentEmailRepository.findById(id);

    }
}
