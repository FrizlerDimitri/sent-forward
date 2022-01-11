package com.oth.sentforward.webapp.dto;

public class NewAddressDto {
    private String emailAddress;

    public NewAddressDto(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public NewAddressDto() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
