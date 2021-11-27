package com.oth.sentforward.persistence.entities;


import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class SavedEmail extends AbstractEmail {

    private Date savedDate;
    private Date lastModified;


    public SavedEmail() {

    }

    public SavedEmail(String content, String subject, EmailAccount from, Collection<EmailAccount> to, Date savedDate, Date lastModified) {
        super(content, subject, from, to);
        this.savedDate = savedDate;
        this.lastModified = lastModified;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
