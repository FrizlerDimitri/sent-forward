package com.oth.sentforward.webapp.restcontroller;

import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CalendarRestController {

    @Autowired
    ICalendarService calendarService;

    @RequestMapping(value = "calendars", method = RequestMethod.POST)
    public Calendar getCalendar(
            @RequestBody EmailAccount emailAccount)
    {
        Optional<Calendar> optionalCalendar= calendarService.getCalendar(emailAccount);
        return optionalCalendar.get();
    }



//    //Todo implement it
//    @RequestMapping("calenders")
//    public Calendar getAllCalendars(MasterAccount masterAccount)
//    {
//        return null;
//    }



}

