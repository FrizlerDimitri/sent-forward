package com.oth.sentforward.persistence.entity.account;

import com.oth.sentforward.persistence.entity.AbstractEntity;
import com.oth.sentforward.persistence.entity.calendar.Calender;
import com.oth.sentforward.persistence.entity.email.ReceivedEmail;
import com.oth.sentforward.persistence.entity.email.SentEmail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;


@Entity
public class EmailAccount extends AbstractEntity<Long> implements IAccount {

    @Column(nullable = false)
    private String emailAddress;

    @Column(name = "pw",nullable = false)
    private String emailPw;

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private int port;

    @Column(nullable = false)
    boolean isVerified=false;

    @ManyToOne
    private MasterAccount masterAccount;

    @OneToMany
    private Collection <SentEmail> sentEmails;

    @OneToMany
    private Collection <ReceivedEmail> receivedEmails;

    @OneToMany
    private Collection <Calender> calenders;


    public EmailAccount() {
    }

    public EmailAccount(String emailAddress, String emailPw, String domain, int port) {
        this.emailAddress = emailAddress;
        this.emailPw = emailPw;
        this.domain = domain;
        this.port = port;
    }


    public EmailAccount(String emailAddress, String emailPw,
                        String domain, int port, boolean isVerified, MasterAccount masterAccount,
                        Collection<SentEmail> sentEmails,
                        Collection<ReceivedEmail> receivedEmails,
                        Collection<Calender> calenders) {

        this.emailAddress = emailAddress;
        this.emailPw = emailPw;
        this.domain = domain;
        this.port = port;
        this.isVerified = isVerified;
        this.masterAccount = masterAccount;
        this.sentEmails = sentEmails;
        this.receivedEmails = receivedEmails;
        this.calenders = calenders;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public MasterAccount getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(MasterAccount masterAccount) {
        this.masterAccount = masterAccount;
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

    public Collection<Calender> getCalenders() {
        return calenders;
    }

    public void setCalenders(Collection<Calender> calenders) {
        this.calenders = calenders;
    }
}
