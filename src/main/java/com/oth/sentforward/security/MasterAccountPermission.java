package com.oth.sentforward.security;


import com.oth.sentforward.persistence.entities.MasterAccount;

import javax.persistence.Entity;


@Entity
public class MasterAccountPermission extends AbstractPermission<MasterAccount, MasterAccountRole> {

    public MasterAccountPermission() {
    }
    public MasterAccountPermission(MasterAccount principal, MasterAccountRole role) {
        super(principal, role);
    }


}
