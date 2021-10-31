package com.oth.sentforward.persistence.entity.userentity;

import javax.persistence.Embeddable;

@Embeddable
public class Address  {


    private int zip;

    private String country;

    private String street;

    private String town;


    public Address(int zip, String country, String street, String town) {
        this.zip = zip;
        this.country = country;
        this.street = street;
        this.town = town;
    }

    public Address() {
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
