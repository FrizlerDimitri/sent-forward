package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.exeption.CalendarNotFoundException;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.repositories.ICalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService implements ICalendarService {



    @Autowired
    private ICalendarRepository calendarRepository;

    @Autowired
    private IAccountService iAccountService;

    public CalendarService(ICalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }


    @Override
    public Optional<Calendar> getCalendar(EmailAccount emailAccount) throws CalendarNotFoundException {

        Optional<EmailAccount> optionalEmailAccount= iAccountService.getEmailAccountByEmailAddress(emailAccount.getEmailAddress());

        if(optionalEmailAccount.isPresent())
        {
            return Optional.of(optionalEmailAccount.get().getCalendar());
        }else {
            throw new CalendarNotFoundException("Calendar for " + emailAccount.getEmailAddress() + " not found");
        }

    }



    //TODO implement it
    @Override
    public Optional<List<Calendar>> getCalendarsByMasterAccountName(MasterAccount masterAccount) {

        return Optional.empty();
    }

    @Override
    public Optional<Calendar> getCalendarById(Long id) {

        return calendarRepository.findCalendarById(id);
    }


}
