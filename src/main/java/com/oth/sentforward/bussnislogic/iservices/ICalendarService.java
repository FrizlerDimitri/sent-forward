package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.bussnislogic.exeption.CalendarNotFoundException;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;

import java.util.List;
import java.util.Optional;

public interface ICalendarService {

    Optional <Calendar> getCalendar(EmailAccount emailAccount);

    Optional <List<Calendar>> getCalendarsByMasterAccountName(MasterAccount masterAccount) throws CalendarNotFoundException;

    Optional <Calendar> getCalendarById(Long id) throws CalendarNotFoundException;

}
