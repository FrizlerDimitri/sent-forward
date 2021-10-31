package com.oth.sentforward.persistence.entity.email;


import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
public class SavedEmail extends AbstractEmail {

    private Date savedDate;

    private Date lastModified;

    public SavedEmail()
    {
        super();
    }

    public SavedEmail(String content, String source, String subject, Collection<String> receivers, Date savedDate, Date lastModified) {
        super(content, source, subject, receivers);
        this.savedDate = savedDate;
        this.lastModified = lastModified;
    }

    public SavedEmail(Date savedDate, Date lastModified) {
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
