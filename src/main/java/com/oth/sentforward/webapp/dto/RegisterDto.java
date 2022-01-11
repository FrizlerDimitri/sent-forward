package com.oth.sentforward.webapp.dto;

public class RegisterDto {

    private String accountName;
    private String password;
    private String passwordConfirm;

    private String lastName;
    private String name;
    private String country;
    private String town;
    private String street;


    public RegisterDto() {
    }

    public RegisterDto(String accountName, String password, String passwordConfirm) {
        this.accountName = accountName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public RegisterDto(String lastName, String name, String country, String town, String street) {
        this.lastName = lastName;
        this.name = name;
        this.country = country;
        this.town = town;
        this.street = street;
    }

    public RegisterDto(String accountName, String password, String passwordConfirm, String lastName, String name, String country, String town, String street) {
        this.accountName = accountName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.lastName = lastName;
        this.name = name;
        this.country = country;
        this.town = town;
        this.street = street;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "RegisterDto{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
