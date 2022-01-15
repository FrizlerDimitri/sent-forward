package com.oth.sentforward.webapp.controller;

import com.oth.sentforward.bussnislogic.iservices.ICalendarService;
import com.oth.sentforward.bussnislogic.services.AccountService;
import com.oth.sentforward.persistence.entities.Appointment;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import com.oth.sentforward.webapp.dto.AppointmentDTO;

import com.oth.sentforward.webapp.dto.OrderDTO;
import de.oth.homeDente.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
public class CalendarController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ICalendarService calendarService;


    @RequestMapping(value = "/account/calendar/{emailId}/{calendarId}")
    public String getCalendar(Model model, Principal principal, @PathVariable("emailId") Long emailId,@PathVariable("calendarId") Long calendarId) {

        accountService.getEmailAccountById(emailId).ifPresent(emailAccount -> {
           model.addAttribute("emailAccount", emailAccount);
        });

        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<Calendar> optionalCalendar = calendarService.getCalendarById(calendarId);
        optionalCalendar.ifPresent(calendar -> {
            model.addAttribute("calendar", calendar);
        });

        return "calendar";
    }

    @RequestMapping(value = "/account/calendar/new-appointment/{calendarId}")
    public String createAppointment(Model model, Principal principal, @PathVariable("calendarId") Long calendarId) {

        model.addAttribute("appointmentDTO", new AppointmentDTO());

        Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
        optionalMasterAccount.ifPresent(acc -> {
            model.addAttribute("masterAccount", acc);
            model.addAttribute("user", acc.getUser());
        });

        Optional<Calendar> optionalCalendar = calendarService.getCalendarById(calendarId);
        optionalCalendar.ifPresent(calendar -> {
            model.addAttribute("calendar", calendar);
        });

        return "createAppointment";
    }

    @RequestMapping(value = "/account/calendar/new-appointment/{calendarId}", method = RequestMethod.POST)
    public String createAppointmentPost(@PathVariable("calendarId") Long calendarId, @ModelAttribute AppointmentDTO appointmentDTO) {

        String name = appointmentDTO.getName();
        String description = appointmentDTO.getDescription();

        String date = appointmentDTO.getDate();
        String start = appointmentDTO.getStartTime();
        String end = appointmentDTO.getEndTime();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Appointment appointment = new Appointment();

        try {
            Date appointmentDate = sdf1.parse(date);

            if( (start != null && !start.equals("")))
            {
                Date startDate = sdf2.parse(date + " " + start);
                appointment.setEndTime(startDate);

            }

            if((end != null && !end.equals("")))
            {
                Date endDate = sdf2.parse(date + " " + end);
                appointment.setStartTime(endDate);
            }

            appointment.setName(name);
            appointment.setDescription(description);
            appointment.setDate(appointmentDate);

            calendarService.getCalendarById(calendarId).ifPresent(calendar -> {

                calendar.getAppointments().add(appointment);
                calendarService.updateCalendar(calendar);

            });

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/account";
    }

    @RequestMapping(value = "/account/calendar/{emailId}/{calendarId}/order-chef")
    public String createAppointmentWithHomeDente(@PathVariable("emailId") Long emailId,@PathVariable("calendarId") Long calendarId, Model model, Principal principal) {

        try {
            List<Chef> chefs = calendarService.getCooksFromHomeDente();

            Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());
            optionalMasterAccount.ifPresent(acc -> {
                model.addAttribute("masterAccount", acc);
                model.addAttribute("user", acc.getUser());
            });

            accountService.getEmailAccountById(emailId).ifPresent(emailAccount -> {
                model.addAttribute("emailAccount", emailAccount);
            });

            Optional<Calendar> optionalCalendar = calendarService.getCalendarById(calendarId);
            optionalCalendar.ifPresent(calendar -> {
                model.addAttribute("calendar", calendar);
            });


            model.addAttribute("chefs", chefs);

            return "chefs";

        } catch (RestClientException restClientException) {
            restClientException.printStackTrace();
        }
        return "redirect:/account/service-error";
    }

    @RequestMapping(value = "/account/calendar/{emailId}/{calendarId}/order-chef/{chefId}")
    public String orderAppointmentWithHomeDente(@PathVariable("emailId") Long emailId,@PathVariable("calendarId") Long calendarId, @PathVariable("chefId") Long chefId,Model model, Principal principal){

        try {

            List<Chef> chefs = calendarService.getCooksFromHomeDente();
            Map<Long, Chef> chefMap =chefs.stream().collect(Collectors.toMap(Chef::getId, Function.identity()));

            Chef chef = chefMap.get(chefId);

            model.addAttribute("chef",chef);

            Optional<MasterAccount> optionalMasterAccount = accountService.loadMasterAccountUserByUsername(principal.getName());

            optionalMasterAccount.ifPresent(acc -> {
                model.addAttribute("masterAccount", acc);
                model.addAttribute("user", acc.getUser());
            });

            accountService.getEmailAccountById(emailId).ifPresent(emailAccount -> {
                model.addAttribute("emailAccount", emailAccount);
            });

            Optional<Calendar> optionalCalendar = calendarService.getCalendarById(calendarId);
            optionalCalendar.ifPresent(calendar -> {
                model.addAttribute("calendar", calendar);
            });



            List<OrderedDish> dishes = new ArrayList<>();


            for(Dish dish : chef.getDishes() )
            {
                OrderedDish orderedDish = new OrderedDish();
                orderedDish.setDish(dish);
                dishes.add(orderedDish);
            }
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrders(dishes);

            model.addAttribute("orderDTO", orderDTO);

            return "orderChef";

        } catch (RestClientException restClientException)
        {
            restClientException.printStackTrace();
        }

        return "redirect:/account/service-error";

    }

    @RequestMapping(value = "/account/calendar/{emailId}/{calendarId}/order-chef/{chefId}", method = RequestMethod.POST)
    public String orderAppointmentWithHomeDentePost(@PathVariable("emailId") Long emailId,@PathVariable("calendarId") Long calendarId, @PathVariable("chefId") Long chefId, @ModelAttribute OrderDTO orderDTO, Principal principal )
    {
            try {

                EmailAccount emailAccount = accountService.getEmailAccountById(emailId).get();

                List<Chef> chefs = calendarService.getCooksFromHomeDente();
                Map<Long, Chef> chefMap =chefs.stream().collect(Collectors.toMap(Chef::getId, Function.identity()));
                Chef chef = chefMap.get(chefId);

                Order order = new Order();
                Customer customer = new Customer();

                customer.setEmail(emailAccount.getEmailAddress());

                order.setOrderDate(new Date());
                order.setOrderedBy(customer);
                order.setOrderedChef(chef);

                order.setJobDate(orderDTO.getDate());
                order.setOrderDetails(orderDTO.getDetails());
                order.setOrderedDishes(orderDTO.getOrders());

                calendarService.orderChefFromHomeDente(order);


                calendarService.getCalendar(emailAccount).ifPresent(calendar -> {

                    Appointment appointment = new Appointment();
                    appointment.setName("Booked Chef from Home Dente");
                    appointment.setDate(orderDTO.getDate());
                    appointment.setDescription(orderDTO.getDetails());

                    calendar.getAppointments().add(appointment);
                    calendarService.updateCalendar(calendar);

                });
                return "redirect:/account/calendar/"+emailId+"/" +calendarId;

            } catch (HttpClientErrorException httpClientErrorException)
            {
                httpClientErrorException.printStackTrace();

            } catch (RestClientException restClientException){

                restClientException.printStackTrace();
            }
        return "redirect:/account/service-error";
    }


}
