package com.oth.sentforward.persistence.entities;

import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class EmailAccount extends AbstractEntity<Long> implements IAccount {

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String emailPw;


    @ManyToOne
    private MasterAccount masterAccount;

    @OneToOne(cascade = CascadeType.ALL)
    private Calendar calendar=new Calendar();

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<SentEmail> sentEmails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ReceivedEmail> receivedEmails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<SavedEmail> savedEmails = new ArrayList<>();

    public EmailAccount() {
    }

    public EmailAccount(String emailAddress, String emailPw) {
        this.emailAddress = emailAddress;
        this.emailPw = emailPw;
    }

    public EmailAccount(String emailAddress, String emailPw, MasterAccount masterAccount) {
        this.emailAddress = emailAddress;
        this.emailPw = emailPw;
        this.masterAccount = masterAccount;
    }

    public EmailAccount(String emailAddress, String emailPw, MasterAccount masterAccount, Calendar calendar, Collection<SentEmail> sentEmails, Collection<ReceivedEmail> receivedEmails, Collection<SavedEmail> savedEmails) {
        this.emailAddress = emailAddress;
        this.emailPw = emailPw;
        this.masterAccount = masterAccount;
        this.calendar = calendar;
        this.sentEmails = sentEmails;
        this.receivedEmails = receivedEmails;
        this.savedEmails = savedEmails;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPw() {
        return emailPw;
    }

    public void setEmailPw(String emailPw) {
        this.emailPw = emailPw;
    }

    public MasterAccount getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(MasterAccount masterAccount) {
        this.masterAccount = masterAccount;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Collection<SentEmail> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(Collection<SentEmail> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public Collection<ReceivedEmail> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(Collection<ReceivedEmail> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public Collection<SavedEmail> getSavedEmails() {
        return savedEmails;
    }

    public void setSavedEmails(Collection<SavedEmail> savedEmails) {
        this.savedEmails = savedEmails;
    }


    @Override
    public String toString() {
        return "EmailAccount{" +
                "emailAddress='" + emailAddress + '\'' +
                ", emailPw='" + emailPw + '\'' +
                ", calendar=" + calendar +
                ", sentEmails=" + sentEmails +
                ", receivedEmails=" + receivedEmails +
                ", savedEmails=" + savedEmails +
                "} " + super.toString();
    }
}
