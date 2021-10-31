package com.oth.sentforward.persistence.entity.email;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class ReceivedEmail extends AbstractEmail {

    private Date received;

    public ReceivedEmail() {
        super();
    }

    public ReceivedEmail(String content, String source, String subject, Collection<String> reciver
    ) {
        super(content, source, subject
                ,reciver
        );
    }

    public ReceivedEmail(String content, String source, String subject, Collection<String> receiver, Date received) {
        super(content, source, subject, receiver);
        this.received = received;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date send) {
        this.received = send;
    }
}
