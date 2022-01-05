package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.repositories.ICalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService implements ICalendarService {



    @Autowired
    private ICalendarRepository iCalendarRepository;

    @Autowired
    private IAccountService iAccountService;

    public CalendarService(ICalendarRepository iCalendarRepository) {
        this.iCalendarRepository = iCalendarRepository;
    }


    @Override
    public Optional<Calendar> getCalendar(EmailAccount emailAccount) {

        Optional<EmailAccount> optionalEmailAccount= iAccountService.getEmailAccountByEmailAddress(emailAccount.getEmailAddress());
        if(optionalEmailAccount.isPresent())
        {
            return Optional.of(optionalEmailAccount.get().getCalendar());
        }
        return Optional.empty();
    }



    //TODO implement it
    @Override
    public Optional<List<Calendar>> getCalendars(MasterAccount masterAccount) {

        return null;
    }


}
