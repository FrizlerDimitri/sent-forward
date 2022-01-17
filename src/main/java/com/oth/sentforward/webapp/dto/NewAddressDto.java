package com.oth.sentforward.webapp.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
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
