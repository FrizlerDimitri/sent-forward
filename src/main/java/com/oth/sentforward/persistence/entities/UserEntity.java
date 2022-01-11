package com.oth.sentforward.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.*;

@Entity
public class UserEntity extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;


    @JsonBackReference
    @OneToOne(mappedBy = "user")
    private MasterAccount masterAccount;

    public UserEntity() {
    }

    public UserEntity(String lastName, String name, Address address) {
        this.lastName = lastName;
        this.name = name;
        this.address = address;
    }

    public UserEntity(String lastName, String name, Address address, MasterAccount masterAccount) {
        this.lastName = lastName;
        this.name = name;
        this.address = address;
        this.masterAccount = masterAccount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
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


    @Override
    public String toString() {
        return "UserEntity{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", masterAccount ID =" + masterAccount.getId() +
                '}';
    }
}
