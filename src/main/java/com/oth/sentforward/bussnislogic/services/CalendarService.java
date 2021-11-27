package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.repositories.ICalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService implements ICalendarService {

    @Autowired
    private ICalendarRepository iCalendarRepository;

    public CalendarService(ICalendarRepository iCalendarRepository) {
        this.iCalendarRepository = iCalendarRepository;
    }
}
