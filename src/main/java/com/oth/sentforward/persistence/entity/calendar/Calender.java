package com.oth.sentforward.persistence.entity.calendar;

import com.oth.sentforward.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Calender extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String name;

    @OneToMany
    private Collection<Appointment> appointments;

    public Calender(String name, Collection<Appointment> appointments) {
        this.name = name;
        this.appointments = appointments;
    }

    public Calender() {
    }

    public String getName() {
        return name;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setName(String name) {
        this.name = name;
    }
}
