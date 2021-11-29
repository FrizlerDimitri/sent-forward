package com.oth.sentforward.security;


import com.oth.sentforward.persistence.AbstractEntity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractPermission<P,R> extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    protected P principal;

    @ManyToOne(fetch = FetchType.EAGER)
    protected R role;

    public AbstractPermission() {
    }

    public AbstractPermission(P principal, R role) {
        this.principal = principal;
        this.role = role;
    }

    public P getPrincipal() {
        return principal;
    }

    public void setPrincipal(P principal) {
        this.principal = principal;
    }

    public R getRole() {
        return role;
    }

    public void setRole(R role) {
        this.role = role;
    }
}
