package com.oth.sentforward.persistence.entities;


import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class Appointment extends AbstractEntity<Long> {

    private String name;

    private Date date;

    private Timestamp startTime;

    private Timestamp endTime;

    private String description;

    private boolean isFulltime;


    @ElementCollection
    private Collection<String> participants;

    public Appointment() {
    }

    public Appointment(String name, Date date, Timestamp startTime, Timestamp endTime, String description, boolean isFulltime, Collection<String> participants) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp start) {
        this.startTime = start;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp end) {
        this.endTime = end;
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
