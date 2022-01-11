package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.bussnislogic.exeption.CanNotSentException;
import com.oth.sentforward.persistence.entities.AbstractEmail;
import com.oth.sentforward.persistence.entities.ReceivedEmail;
import com.oth.sentforward.persistence.entities.SavedEmail;
import com.oth.sentforward.persistence.entities.SentEmail;

import java.util.Optional;

public interface IEmailService {

    Optional<SentEmail> getSentEmailByID(Long id);

    Optional<ReceivedEmail> getReceivedEmailById(Long id);

    Optional<SavedEmail> getSavedEmailById(Long id);

    Optional <SentEmail> sentEmail(SentEmail email) throws CanNotSentException;


}
