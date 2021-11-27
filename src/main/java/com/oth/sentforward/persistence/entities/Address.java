package com.oth.sentforward.persistence.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String country;
    private String street;
    private String town;

    public Address(String country, String street, String town) {
        this.country = country;
        this.street = street;
        this.town = town;
    }

    public Address() {
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

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
