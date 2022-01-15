package com.oth.sentforward.bussnislogic.iservices;

import com.oth.sentforward.bussnislogic.exeption.CalendarNotFoundException;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import de.oth.homeDente.entity.Chef;
import de.oth.homeDente.entity.Order;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

public interface ICalendarService {

    Optional <Calendar> getCalendar(EmailAccount emailAccount);

    Optional <List<Calendar>> getCalendarsByMasterAccountName(MasterAccount masterAccount) throws CalendarNotFoundException;

    Optional <Calendar> getCalendarById(Long id) throws CalendarNotFoundException;

    Optional <Calendar> updateCalendar(Calendar calendar);

    Order orderChefFromHomeDente(Order order) throws HttpClientErrorException, RestClientException;

    List<Chef> getCooksFromHomeDente() throws RestClientException;

}
