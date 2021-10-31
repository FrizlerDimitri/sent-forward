package com.oth.sentforward.persistence.entity.email;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class SentEmail extends AbstractEmail {

    private Date send;

    public SentEmail()
    {
        super();
    }

    public SentEmail(String content, String source, String subject, Collection<String> reciver) {
        super(content, source, subject
                , reciver
        );
    }


    public SentEmail(String content, String source, String subject, Collection<String> reciver, Date send) {
        super(content, source, subject
                , reciver
        );
        this.send = send;
    }

    public Date getSend() {
        return send;
    }

    public void setSend(Date send) {
        this.send = send;
    }
}
