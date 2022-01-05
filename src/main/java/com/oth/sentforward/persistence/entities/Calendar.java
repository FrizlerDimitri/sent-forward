package com.oth.sentforward.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oth.sentforward.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Calendar extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String name="Calendar";

    @OneToMany
    private Collection<Appointment> appointments= new ArrayList<>();


    public Calendar(String name, Collection<Appointment> appointments) {
        this.name = name;
        this.appointments = appointments;
    }


    public Calendar(String name) {
        this.name = name;
    }

    public Calendar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }
}
