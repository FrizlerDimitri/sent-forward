package com.oth.sentforward.persistence.entity.account;

import com.oth.sentforward.persistence.entity.AbstractEntity;
import com.oth.sentforward.persistence.entity.userentity.UserEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class MasterAccount extends AbstractEntity<Long> implements IAccount {


    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "pw", nullable = false)
    private String password;


    @OneToOne(mappedBy = "masterAccount")
    private UserEntity user;

    @OneToMany
    Collection<EmailAccount> emailAccounts= new ArrayList<EmailAccount>();


    public MasterAccount(String name, String password, UserEntity user, Collection<EmailAccount> emailAccounts) {
        this.name = name;
        this.password = password;
        this.user = user;
        this.emailAccounts = emailAccounts;
    }

    public MasterAccount(String name, String password, UserEntity user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }

    //TODO Constructor for testing
    public MasterAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public MasterAccount() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", emailAccounts=" + emailAccounts +
                '}';
    }
}
