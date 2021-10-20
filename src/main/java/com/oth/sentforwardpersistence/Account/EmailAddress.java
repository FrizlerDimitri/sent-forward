package com.oth.sentforwardpersistence.Account;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EmailAddress implements Serializable {


    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email_address_name", nullable = false)
    private String emailAddressName;

    @Column(name = "email_address_pw", nullable = false)
    private String emailAddressPw;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "host", nullable = false)
    private String host;


    @Column(name = "isverified", nullable = false)
    private boolean isVerified;

    @ManyToOne
    private Account account;



    public EmailAddress() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddressName() {
        return emailAddressName;
    }

    public void setEmailAddressName(String emailAddressName) {
        this.emailAddressName = emailAddressName;
    }

    public String getEmailAddressPw() {
        return emailAddressPw;
    }

    public void setEmailAddressPw(String emailAddressPw) {
        this.emailAddressPw = emailAddressPw;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
