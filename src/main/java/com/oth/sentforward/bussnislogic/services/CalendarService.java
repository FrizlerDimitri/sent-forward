package com.oth.sentforward.bussnislogic.services;

import com.oth.sentforward.bussnislogic.exeption.CalendarNotFoundException;
import com.oth.sentforward.bussnislogic.iservices.IAccountService;
import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.persistence.repositories.ICalendarRepository;
import de.oth.homeDente.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class CalendarService implements ICalendarService {



    @Autowired
    private ICalendarRepository calendarRepository;

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private RestTemplate restServiceClient;


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



    @Override
    public Optional<List<Calendar>> getCalendarsByMasterAccountName(MasterAccount masterAccount) {

        return Optional.empty();
    }

    @Override
    public Optional<Calendar> getCalendarById(Long id) {
        return calendarRepository.findCalendarById(id);
    }

    @Override
    public Optional<Calendar> updateCalendar(Calendar calendar) {
        return Optional.of(calendarRepository.save(calendar));
    }


    @Override
    public Order orderChefFromHomeDente(Order order) throws HttpClientErrorException, RestClientException
    {
        final String OTH_HOST_HOME_DENTE = "http://im-codd.oth-regensburg.de:8966/";
        final String LOCAL_HOST = "http://localhost:8081/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity <Order> entity = new HttpEntity<>(order, headers);
            ResponseEntity<Order> responseEntity = restServiceClient.postForEntity(OTH_HOST_HOME_DENTE+"restapi/orderChef",entity, Order.class);

            return responseEntity.getBody();

    }


    @Override
    public List<Chef> getCooksFromHomeDente() throws RestClientException {

        final String OTH_HOST_HOME_DENTE = "http://im-codd.oth-regensburg.de:8966/";
        final String LOCAL_HOST = "http://localhost:8081/";

        List<Chef> chefs =  Arrays.asList(restServiceClient.getForObject(OTH_HOST_HOME_DENTE+"restapi/chefs", Chef[].class));

        return chefs;

    }



}
