package com.oth.sentforward.persistence.entities;

import com.oth.sentforward.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Calendar extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String name;

    @OneToMany
    private Collection<Appointment> appointments;

    public Calendar(String name, Collection<Appointment> appointments) {
        this.name = name;
        this.appointments = appointments;
    }


    public Calendar(String name) {
        this.name = name;
    }

    public Calendar() {
    }
}
