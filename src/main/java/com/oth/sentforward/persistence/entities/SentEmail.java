package com.oth.sentforward.persistence.entities;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class SentEmail extends AbstractEmail{

    private Date sentDate;

    public SentEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to, Date sentDate) {
        super(content, subject, from, to);
        this.sentDate = sentDate;
    }

    public SentEmail() {

    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }


}
