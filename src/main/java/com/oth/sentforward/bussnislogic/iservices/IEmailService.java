package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.persistence.entities.SentEmail;

import java.util.Optional;

public interface IEmailService {

    Optional<SentEmail> getSentEmailByID(Long id);

}
