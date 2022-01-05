package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.repositories.IMasterAccountRepository;

import java.util.List;
import java.util.Optional;

public interface ICalendarService {

    Optional <Calendar> getCalendar(EmailAccount emailAccount);

    Optional <List<Calendar>> getCalendars(MasterAccount masterAccount);

}
