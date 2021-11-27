package com.oth.sentforward.persistence.entities;

import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class EmailAccount extends AbstractEntity<Long> implements IAccount {

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String emailPw;


    @ManyToOne
    private MasterAccount masterAccount;

    @OneToOne
    private Calendar calendar;

    @OneToMany
    private Collection<SentEmail> sentEmails;

    @OneToMany
    private Collection<ReceivedEmail> receivedEmails;

    @OneToMany
    private Collection<SavedEmail> savedEmails;


}
