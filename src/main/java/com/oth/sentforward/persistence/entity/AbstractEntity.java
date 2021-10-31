package com.oth.sentforward.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }


}
