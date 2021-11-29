package com.oth.sentforward.persistence.entities;

import com.oth.sentforward.persistence.AbstractEntity;
import com.oth.sentforward.security.Authority;
import com.oth.sentforward.security.EnumAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MasterAccount extends AbstractEntity<Long> implements IAccount  , UserDetails {


    @Column(unique = true, nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany
    private Collection<EmailAccount> emailAccounts= new ArrayList<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        //TODO TODO right implementation
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add( new Authority(EnumAuthority.MASTER_ACCOUNT_CREATE.toString()));
        authorities.add( new Authority(EnumAuthority.MASTER_ACCOUNT_READ.toString()));
        authorities.add( new Authority(EnumAuthority.MASTER_ACCOUNT_DELETE.toString()));
        authorities.add( new Authority(EnumAuthority.MASTER_ACCOUNT_WRITE.toString()));

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
