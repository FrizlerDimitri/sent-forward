package com.oth.sentforwardpersistence.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="Account")
public class Account implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long accountId;



    public Account(){

    }

    public Account(Long accountId, String accountName, String accountPw) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPw = accountPw;
    }

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_pw", nullable = false)
    private String accountPw;

    @OneToMany
    private Collection<EmailAddress> emailAddresses;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPw() {
        return accountPw;
    }

    public void setAccountPw(String accountPw) {
        this.accountPw = accountPw;
    }


    @Override
    public String toString() {
        return "Account {" +
                "accountId=" + accountId +
                ", accountName='" + accountName+
                ", accountPw='" + accountPw +
                '}';
    }
}
