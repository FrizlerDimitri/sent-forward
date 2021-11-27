package com.oth.sentforward.persistence.entities;


import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;


@MappedSuperclass
public class AbstractEmail extends AbstractEntity<Long> {

    private String content;
    private String subject;

    @OneToOne
    private EmailAccount from;

    @OneToMany
    private Collection<EmailAccount> to;

    public AbstractEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to) {
        this.content = content;
        this.subject = subject;
        this.from = from;
        this.to = to;
    }

    public AbstractEmail() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EmailAccount getFrom() {
        return from;
    }

    public void setFrom(EmailAccount from) {
        this.from = from;
    }

    public Collection<EmailAccount> getTo() {
        return to;
    }

    public void setTo(Collection<EmailAccount> to) {
        this.to = to;
    }

}
