package com.oth.sentforward.webapp.restcontroller;

import com.oth.sentforward.bussnislogic.exeption.CalendarNotFoundException;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CalendarRestController {

    @Autowired
    ICalendarService calendarService;

    @RequestMapping(value = "/sentforward-rest-api/calendars", method = RequestMethod.POST)
    public ResponseEntity getCalendarAPI(@RequestBody EmailAccount emailAccount)
    {
        try
        {
            return new ResponseEntity(calendarService.getCalendar(emailAccount).get(), HttpStatus.OK);
        } catch (CalendarNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    //Todo implement it
//    @RequestMapping(value = "sentforward-rest-api/calendarsAll", method = RequestMethod.POST)
//    public List<Calendar> getAllCalendars(@RequestBody MasterAccount masterAccount)
//    {
//        Optional<List<Calendar>> optionalCalendars = calendarService.getCalendarsByMasterAccountName(masterAccount);
//        return optionalCalendars.get();
//    }



}

