package com.oth.sentforward.persistence.entity.userentity;

import com.oth.sentforward.persistence.entity.AbstractEntity;
import com.oth.sentforward.persistence.entity.account.MasterAccount;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class UserEntity extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;


    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    @OneToOne
    private MasterAccount masterAccount;

    public UserEntity(String name, String lastName, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }


    public UserEntity(String name, String lastName, Address address, MasterAccount masterAccount) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.masterAccount = masterAccount;
    }


    public UserEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MasterAccount getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(MasterAccount masterAccount) {
        this.masterAccount = masterAccount;
    }

}
