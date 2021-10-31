package com.oth.sentforward.persistence.entity.email;

import com.oth.sentforward.persistence.entity.AbstractEntity;

import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@MappedSuperclass
public abstract class AbstractEmail extends AbstractEntity<Long> {

    private String content;

    private String source;

    private String subject;

    @ElementCollection
    private Collection<String> receivers;


    public AbstractEmail(String content, String source, String subject, Collection<String> receivers
    ) {
        this.content = content;
        this.source = source;
        this.subject = subject;
        this.receivers = receivers;
    }

    public AbstractEmail() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Collection<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(Collection<String> receivers) {
        this.receivers = receivers;
    }
}
