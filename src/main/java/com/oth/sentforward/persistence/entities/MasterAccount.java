package com.oth.sentforward.persistence.entities;

import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class MasterAccount extends AbstractEntity<Long> implements IAccount {


    @Column(unique = true, nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany
    private Collection<EmailAccount> emailAccounts;

    public MasterAccount() {
    }

    public MasterAccount(String accountName, String password, UserEntity user) {
        this.accountName = accountName;
        this.password = password;
        this.user = user;
    }

    public MasterAccount(String accountName, String password, UserEntity user, Collection<EmailAccount> emailAccounts) {
        this.accountName = accountName;
        this.password = password;
        this.user = user;
        this.emailAccounts = emailAccounts;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Collection<EmailAccount> getEmailAccounts() {
        return emailAccounts;
    }

    public void setEmailAccounts(Collection<EmailAccount> emailAccounts) {
        this.emailAccounts = emailAccounts;
    }

    @Override
    public String toString() {
        return "MasterAccount{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", user ID =" + user.getId() +
                ", emailAccounts=" + emailAccounts +
                '}';
    }
}
