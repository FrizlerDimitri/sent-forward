package com.oth.sentforward.persistence.entity.calendar;


import com.oth.sentforward.persistence.entity.AbstractEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class Appointment extends AbstractEntity<Long> {

private String name;

private Date date;

private Timestamp AppointmentStart;

private Timestamp AppointmentEnd;

private String description;

private boolean isFulltime;

@ElementCollection
private Collection<String> participants ;

    public Appointment() {
    }

    public Appointment(String name, Date date, Timestamp AppointmentStart, Timestamp AppointmentEnd, String description, boolean isFulltime
                        , Collection<String> participants
    ) {
        this.name = name;
        this.date = date;
        this.AppointmentStart = AppointmentStart;
        this.AppointmentEnd = AppointmentEnd;
        this.description = description;
        this.isFulltime = isFulltime;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getAppointmentStart() {
        return AppointmentStart;
    }

    public void setAppointmentStart(Timestamp start) {
        this.AppointmentStart = start;
    }

    public Timestamp getAppointmentEnd() {
        return AppointmentEnd;
    }

    public void setAppointmentEnd(Timestamp end) {
        this.AppointmentEnd = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFulltime() {
        return isFulltime;
    }

    public void setFulltime(boolean fulltime) {
        isFulltime = fulltime;
    }


    public Collection<String> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<String> participants) {
        this.participants = participants;
    }
}
