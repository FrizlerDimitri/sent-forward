package com.oth.sentforward.webapp.controller;

import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;


@Controller
public class CalendarController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ICalendarService calendarService;



    @RequestMapping(value="/account/calendar/{calendarId}")
    public String getCalendar(Model model, Principal principal, @PathVariable("calendarId") Long calendarId)
    {

        Optional<MasterAccount> optionalMasterAccount=accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<Calendar> optionalCalendar=calendarService.getCalendarById(calendarId);
        optionalCalendar.ifPresent(calendar -> {
            model.addAttribute("calendar", calendar);
        });

        return "calendar";
    }

}
