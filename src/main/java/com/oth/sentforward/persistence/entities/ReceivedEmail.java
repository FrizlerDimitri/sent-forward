package com.oth.sentforward.persistence.entities;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class ReceivedEmail extends AbstractEmail{

    private Date receivedDate;

    private boolean wasRead;

    public ReceivedEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to, Date receivedDate) {
        super(content, subject, from, to);
        this.receivedDate = receivedDate;
    }

    public ReceivedEmail()
    {

    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public boolean wasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
}
